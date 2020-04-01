
$(document).ready(function () {
    $(".editNote").keyup(function ()
    {
        console.log($("#noteForm").serialize());
        $.post("notes?action=Save", $("#noteForm").serialize(), function (dataBack) {

        });
    });

    setInterval(function ()
    {
        console.log($("#noteForm").serialize());
        $.get("notes", function (dataBack) {

        });
    }, 5000);
});
