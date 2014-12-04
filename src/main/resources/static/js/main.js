$(function() {
	var firstPage = {};
	var viewModel = {};

	viewModel.cards = ko.observableArray([]);
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
			$.get('http://localhost:8080/cards/search', {
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
		url : 'http://localhost:8080/api/v1/cards',
		success : function(data) {
			viewModel.updateData(data);
			firstPage = data;
		},
		async : false
	});

});