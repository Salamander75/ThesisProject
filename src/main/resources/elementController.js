(function() {
    initializeModalBoxes();
    var prev;
    var pressedBoardKey;
    var elementId;
    var elementClass;
    var elementName;
    var elementCssSelector;
    var elementXPath;
    var modal = document.getElementById('elementSelectionArea');
    var span = document.getElementById("closeModalBox");
    var documentBody = document.body;

    if (documentBody) {
        documentBody.addEventListener('mouseover', handler, false);
        documentBody.addEventListener('click', handleMouseClickEvent);
        documentBody.addEventListener('keydown', handleKeyDownEvent)
    }
    else if (documentBody.attachEvent) {               // If browser doesn't support addEventListener
        documentBody.attachEvent('mouseover', function (e) {
            return handler(e || window.event);
        });
    }
    else {
        documentBody.onmouseover = handler;
    }

    function handler(event) {
        if (prev) {
            prev.className = prev.className.replace(/\bhighlight\b/, '');
            prev = undefined;
        }
        if (event.target !== modal && !modal.contains(event.target)) {
            prev = event.target;
            prev.className += " highlight";
        }
    }

    function handleMouseClickEvent(e) {
        if (pressedBoardKey == 17 && e.which == 1) {
            e.preventDefault();
            openModalBox(e);
            pressedBoardKey = undefined;
        }
    }

    function handleKeyDownEvent(e) {
        if (e.which == 17) {
            pressedBoardKey = 17;
        }
    }

    function openModalBox(event) {
        var elementUnderInspection = event.target;
        if (event.target !== modal && !modal.contains(event.target)) {
            clearModal();
            elementId = elementUnderInspection.id;
            elementClass = elementUnderInspection.className.replace(/\bhighlight\b/, '').trim(); // replace given target's highlight class with none
            elementName = elementUnderInspection.name;
            elementCssSelector = myCssSelector(elementUnderInspection);
            elementXPath = generateElementXPath(elementUnderInspection);
            modal.style.display = "block";
            if (elementId != undefined) {
                document.getElementById('htmlElementId').value = elementId;
            }
            if (elementClass != undefined) {
                document.getElementById('htmlElementClass').value = elementClass;
            }
            if (elementName != undefined) {
                document.getElementById('htmlElementName').value = elementName;
            }
            if (elementCssSelector != undefined) {
                document.getElementById('htmlElementCssSelector').value = elementCssSelector;
            }
            if (elementXPath != undefined) {
                document.getElementById('htmlElementXPath').value = elementXPath;
            }
            if (elementUnderInspection == modal ) {
                modal.style.display = "none";
            }
        }
        if (elementUnderInspection == span) {
            modal.style.display = "none";
        }
    }

})();

function createElementObject() {
    var elementId = document.getElementById('htmlElementId').value;
    var elementClassName = document.getElementById('htmlElementClass').value;
    var elementName = document.getElementById('htmlElementName').value;
    var elementSelector = document.getElementById('htmlElementCssSelector').value;
    var elementXPath = document.getElementById('htmlElementXPath').value;
    var obj = '{'
        +'"id" : "' + elementId + '",'
        +'"className"  : "' + elementClassName + '",'
        +'"name" : "' + elementName + '",'
        +'"selector" : "' + elementSelector + '",'
        +'"xpath" : "' + elementXPath + '"'
        +'}';
    gateway.receiveUserSelection(obj);
}

function closeModal() {
    document.getElementById('elementSelectionArea').style.display = "none";
}

function clearModal() {
    document.getElementById('htmlElementId').value = '';
    document.getElementById('htmlElementClass').value = '';
    document.getElementById('htmlElementName').value = '';
    document.getElementById('htmlElementCssSelector').value = '';
    document.getElementById('htmlElementXPath').value = '';
}

function initializeModalBoxes() {
    var body = "document.getElementsByTagName('body')[0]";
    var modalHtml = "<div id='someId' class='modal-content'>" +
        "<div class='model-header'>" +
        "<button id='closeModalBox' onclick='closeModal()' class='close'>&times;</button><h2>Element Inspector</h2>" +
        "</div>"+
        "<div class='modal-body'>" +
        "<table>" +
        "<tr>" +
        "<td><label for='htmlElementId'>ID: </label></td>" +
        "<td><input type='text' name='htmlElementId' id='htmlElementId' /></td>" +
        "</tr>"+
        "<tr>" +
        "<td><label for='htmlElementClass'>ClassName: </label></td>" +
        "<td><input type='text' name='htmlElementClass' id='htmlElementClass' /></td>" +
        "</tr>"+
        "<tr>" +
        "<td><label for='htmlElementClass'>Name: </label></td>" +
        "<td><input type='text' name='htmlElementName' id='htmlElementName' /></td>" +
        "</tr>"+
        "<tr>" +
        "<td><label for='htmlElementCssSelector'>CssSelector: </label></td>" +
        "<td><input type='text' name='htmlElementCssSelector' id='htmlElementCssSelector' /></td>" +
        "</tr>" +
        "<tr>" +
        "<td><label for='htmlElementXPath'>XPath: </label></td>" +
        "<td><input type='text' name='htmlElementXPath' id='htmlElementXPath' /></td>" +
        "</tr>" +
        "<tr>" +
        "<td><button onclick='createElementObject()'>Select</button></td>" +
        "</tr>" +
        "</table>" +
        "</div>" +
        "<div class='modal-footer'><h3>Modal Footer</h3></div></div>";
    var modalDiv = document.createElement('div');
    modalDiv.id = 'elementSelectionArea';
    modalDiv.className = 'modal';    // maybe it is needed better class name for uniqnuess
    modalDiv.innerHTML = modalHtml;

    document.body.appendChild(modalDiv);

    var modalCss = "/* The Modal (background) */\n" +
        ".modal {\n" +
        "    display: none; /* Hidden by default */\n" +
        "    position: fixed; /* Stay in place */\n" +
        "    z-index: 1; /* Sit on top */\n" +
        "    padding-top: 100px; /* Location of the box */\n" +
        "    left: 0;\n" +
        "    top: 0;\n" +
        "    width: 100%; /* Full width */\n" +
        "    height: 100%; /* Full height */\n" +
        "    overflow: auto; /* Enable scroll if needed */\n" +
        "    background-color: rgb(0,0,0); /* Fallback color */\n" +
        "    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */\n" +
        "}\n" +
        "\n" +
        "/* Modal Content */\n" +
        ".modal-content {\n" +
        "    position: relative;\n" +
        "    background-color: #fefefe;\n" +
        "    margin: auto;\n" +
        "    padding: 0;\n" +
        "    border: 1px solid #888;\n" +
        "    width: 40%;\n" +
        "    box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19);\n" +
        "    -webkit-animation-name: animatetop;\n" +
        "    -webkit-animation-duration: 0.4s;\n" +
        "    animation-name: animatetop;\n" +
        "    animation-duration: 0.4s\n" +
        "}\n" +
        "\n" +
        "/* Add Animation */\n" +
        "@-webkit-keyframes animatetop {\n" +
        "    from {top:-300px; opacity:0} \n" +
        "    to {top:0; opacity:1}\n" +
        "}\n" +
        "\n" +
        "@keyframes animatetop {\n" +
        "    from {top:-300px; opacity:0}\n" +
        "    to {top:0; opacity:1}\n" +
        "}\n" +
        "\n" +
        "/* The Close Button */\n" +
        ".close {\n" +
        "    color: white;\n" +
        "    float: right;\n" +
        "    font-size: 14px;\n" +
        "    font-weight: bold;\n" +
        "}\n" +
        "\n" +
        ".close:hover,\n" +
        ".close:focus {\n" +
        "    color: #000;\n" +
        "    text-decoration: none;\n" +
        "    cursor: pointer;\n" +
        "}\n" +
        "\n" +
        ".modal-header {\n" +
        "    padding: 2px 16px;\n" +
        "    background-color: #5cb85c;\n" +
        "    color: white;\n" +
        "}\n" +
        "\n" +
        ".modal-body {padding: 2px 16px;}\n" +
        "\n" +
        ".modal-footer {\n" +
        "    padding: 2px 16px;\n" +
        "    background-color: #5cb85c;\n" +
        "    color: white;\n" +
        "}";

    var modalStyle = document.createElement('style');
    modalStyle.type = 'text/css';
    modalStyle.appendChild(document.createTextNode(modalCss));
    document.head.appendChild(modalStyle);
}

function generateElementCssSelector(el) {
    var names = [];
    while (el.parentNode){
        if (el.id){
            names.unshift('#'+el.id);
            break;
        }else {
            if (el==el.ownerDocument.documentElement) names.unshift(el.tagName.toLowerCase());
            else {
                for (var c=1,e=el;e.previousElementSibling;e=e.previousElementSibling,c++);
                names.unshift(el.tagName.toLowerCase()+":nth-child("+c+")");
            }
            el=el.parentNode;
        }
    }
    return names.join(" > ");
}

function myCssSelector(element) {
    if (element.id !== '' && isElementIdUnique(element.id) == true) {
     //   alert(element.id);
        return '#' + element.id;
    }
    var index= 0;
    var siblings= element.parentNode.childNodes;
    for (var i= 0; i<siblings.length; i++) {
        var sibling= siblings[i];
        if (sibling===element)
            return myCssSelector(element.parentNode)+' > '+element.tagName.toLowerCase() +'['+(index+1)+']';
        if (sibling.nodeType===1 && sibling.tagName===element.tagName)
            index++;
    }
}

function generateElementXPath(element) {
    var elementTagName = element.tagName.toLowerCase();
    if (element.id !== '' && isElementIdUnique(element.id) == true) {
        return '//' + elementTagName + '[@id=\'' + element.id + '\']';
    } else if (element.getAttribute("name") !== null &&
        isElementNameUnique(element.getAttribute("name")) == true) {
        return '//' + elementTagName + '[@name=\'' + element.getAttribute("name") + '\']';
    }

    if (element===document.body) return element.tagName.toLowerCase();

    var index= 0;
    var siblings= element.parentNode.childNodes;
    for (var i= 0; i<siblings.length; i++) {
        var sibling= siblings[i];
        if (sibling===element)
            return generateElementXPath(element.parentNode)+'/'+element.tagName.toLowerCase() +'['+(index+1)+']';
        if (sibling.nodeType===1 && sibling.tagName===element.tagName)
            index++;
    }
}

function isElementIdUnique(id) {
    // Vb vaja täisutada. nt Kui on ka label sama id-ga, et siis seda ei võtaks arvesse
    if(document.querySelectorAll("[id=" + id + "]").length > 1) return false;
    return true;
}

function isElementNameUnique(name) {
    if(document.querySelectorAll("[name=" + name + "]").length > 1) return false;
    return true;
}