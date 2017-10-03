(function() {
    var prev;
	var documentBody = document.body;
	
    if (documentBody) {
        documentBody.addEventListener('mouseover', handler, false);
		documentBody.addEventListener('click', checkElementAvaialbleAttributes);
    }
    else if (documentBody.attachEvent) {
        documentBody.attachEvent('mouseover', function(e) {
            return handler(e || window.event);
        });
    }
    else {
        documentBody.onmouseover = handler;
    }

    function handler(event) {
        if (event.target === document.body ||
            (prev && prev === event.target)) {
            return;
        }
        if (prev) {
            prev.className = prev.className.replace(/\bhighlight\b/, '');
            prev = undefined;
        }
        if (event.target) {
            prev = event.target;
            prev.className += " highlight";
        }
    }
	
	function checkElementAvaialbleAttributes(e) {
		alert(e.target.name);
	}
	
})();

