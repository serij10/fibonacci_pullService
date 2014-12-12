<!DOCTYPE HTML>
<html>
<head>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<script>
	$(document)
			.ready(
					function() {
						var jobNum = 0;
						var maxNum;
						var interval = 10000;
						function polling() {
							$.ajax({
										type : 'get', // it's easier to read GET request parameters
										url : 'servlet',
										dataType : 'JSON',
										data : {
											jobNumber : jobNum,
											max : maxNum,
										},
										success : function(responseJson) {

											$('#index').hide();
											jobNum = responseJson[1];

											$('#div1').html("Your Job Number: " + jobNum);

											var result = responseJson[0];

											if (result != '0') {

												$('#div2').html("Fibonacci Sequence: " + responseJson[0]);

												clearInterval(call);

											} else {
												$('#div2').html("Page will refresh in 10 seconds");

											}

										},
										error : function(data) {
											alert('Failed');
										}
									});

						}
						function getMax() {
							return $('#maxNum').val();

						}
						$('#myBtn').click(function(event) {
							maxNum = getMax();
							polling();
							call = setInterval(polling, interval);
						});
					});
</script>

<title>My JSP 'index.jsp' starting page</title>


</head>

<body>
	<div id="index">
		<h2>Enter Fibonacci sequence length</h2>
		<input type="text" id="maxNum" /> <br> <label>value
			[1-100]</label><br>
		<button id="myBtn">Submit</button>
	</div>
	<div id="div1"></div>
	<div id="div2"></div>

</body>
</html>
