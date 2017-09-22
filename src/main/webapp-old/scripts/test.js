function testClick() {
     $.ajax({
        url: "test",
        type: "POST",
        data: {
            "bar":"foo"
        },
        success: function (response) {
        	console.log(response);
		},
        error: function () {
            console.log("ERROR!!");
        }
    });
}
