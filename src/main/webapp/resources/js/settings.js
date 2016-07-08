$(document).ready(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    var baseURL = $("meta[name='baseURL']").attr("content");

    $(document).ajaxSend(function (e, xhr, options) {
        options.url = baseURL + options.url;
        xhr.setRequestHeader(header, token);
    });
});


