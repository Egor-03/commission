document.addEventListener('DOMContentLoaded', function() {
var elems = document.querySelectorAll('.dropdown-trigger');
var instances = M.Dropdown.init(elems, {});
});
document.addEventListener('DOMContentLoaded', function() {
var elems = document.querySelectorAll('.sidenav');
var instances = M.Sidenav.init(elems, {});
});
document.addEventListener('DOMContentLoaded', function() {
M.FormSelect.init(document.querySelectorAll('select'), {});
});
function sendHTTPDelete(url) {
	fetch(window.location.origin + url, {
		method: 'DELETE',
	}).then(() => {
		window.location.reload()
	});
}