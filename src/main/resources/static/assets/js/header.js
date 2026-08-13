/* Shared site header behaviors: sticky on scroll + mobile menu toggle.
   Vanilla JS on purpose: works on pages that do not load jQuery. */
(function () {
    'use strict';
    var header = document.getElementById('siteheader');
    if (!header) {
        return;
    }
    var nav = header.querySelector('.navbar');
    var collapse = header.querySelector('.navbar-collapse');
    var toggle = header.querySelector('.navbar-toggle');

    if (nav) {
        var stick = function () {
            nav.classList.toggle('sticked', window.scrollY > 40);
        };
        window.addEventListener('scroll', stick, { passive: true });
        stick();
    }

    if (toggle && collapse) {
        toggle.addEventListener('click', function () {
            collapse.classList.toggle('in');
        });
    }
})();