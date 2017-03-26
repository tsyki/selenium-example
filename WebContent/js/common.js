function sendAjaxRequest(target){
	var url="NopService";
	$.post(url,target,postCallBack);
}

function postCallBack(result){
	$('#text1').val(result);
}
