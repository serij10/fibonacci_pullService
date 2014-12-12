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
							$
									.ajax({
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

											$('#div1').html(
													"Your Job Number: "
															+ jobNum);

											var result = responseJson[0];

											if (result != '0') {

												$('#div2')
														.html("Fibonacci Sequence: "+ responseJson[0]);
												$('#myBtnRestart').css("visibility","visible");
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
						$('#myBtnRestart').click(function(event) {
							//$('#div1').hide();
							//$('#div2').hide();
							//$('#index').show();
							$('#myBtnRestart').css("visibility", "hidden");
						})
					});
</script>

<title>Fibonacci Sequence</title>


</head>

<body>
	<div id="index">
		<h2>Enter Fibonacci sequence length</h2>
		<input type="text" id="maxNum" /> <br> <label>value
			[1-100]</label><br>
		<button id="myBtn" type="submit">Submit</button>
	</div>
	<a href="http://localhost:8080/FibonacciSequence_PullService/index.jsp"><button id="myBtnRestart" style="visibility: hidden">Restart</button></a>
	<div id="div1"></div>
	<div id="div2"></div>


</body>
</html>
