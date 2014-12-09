$(function() {
	var firstPage = {};
	var viewModel = {};

	viewModel.cards = ko.observableArray([]);
	viewModel.renderedCards = ko.computed(function() {
		var data = viewModel.cards();
		var times = Math.ceil(data.length / 3);
		var result = [];
		for (var i = 0; i < times; i++) {
			result.push(data.slice(i * 3, i * 3 + 3));
		}
		return result;
	});

	viewModel.isTypingInQueryInput = ko.observable(false);

	viewModel.query = ko.observable();
	viewModel.query.subscribe(function(value) {
		this.isTypingInQueryInput(true);
		this.cards([]);
	}, viewModel);

	viewModel.delayedQuery = ko.computed(viewModel.query).extend({
		rateLimit : {
			method : "notifyWhenChangesStop",
			timeout : 550
		}
	});

	viewModel.delayedQuery.subscribe(function(value) {
		var search = value.toLowerCase().trim();

		if (search === '') {
			viewModel.updateData(firstPage);
		} else {
			$.get('http://localhost:8080/api/v1/cards/search?page=0&size=21', {
				query : search
			}).done(function(data) {
				viewModel.updateData(data);
			});
		}

	}, viewModel);

	viewModel.updateData = function(data) {
		viewModel.cards(data.content);
		viewModel.isTypingInQueryInput(false);
	};

	ko.applyBindings(viewModel);

	$.ajax({
		url : 'http://localhost:8080/api/v1/cards?page=0&size=21',
		success : function(data) {
			viewModel.updateData(data);
			firstPage = data;
		},
		async : false
	});

});