(function () {
    'use strict';

    angular.module('MenuApp')
        .controller('MenuAppController', MenuAppController)
        .constant('ApiBasePath', "https://davids-restaurant.herokuapp.com");

    MenuAppController.$inject = ['MenuDataService', 'randomCategoryShortName'];
    function MenuAppController(MenuDataService, randomCategoryShortName) {
        var $ctrl = this;

        $ctrl.categories = [];
        $ctrl.items = [];

        $ctrl.loadMenuCategories = function () {
            MenuDataService.getAllCategories()
                .then(function (categories) {
                    $ctrl.categories = categories;
                });
        };

        $ctrl.loadMenuItems = function (categoryShortName) {
            MenuDataService.getItemsForCategory(categoryShortName)
                .then(function (items) {
                    $ctrl.items = items;
                });
        };

        $ctrl.loadRandomCategoryItems = function () {
            var categoryShortName = getRandomCategoryShortName();
            $ctrl.loadMenuItems(categoryShortName);
        };

        function getRandomCategoryShortName() {
            var randomIndex = Math.floor(Math.random() * $ctrl.categories.length);
            return $ctrl.categories[randomIndex].short_name;
        }
    }
})();
