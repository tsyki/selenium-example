var url="NopService";

function asyncChangeValue(targetId,value){
	$.post(url,targetId,function(result){
		$("#" + targetId).val(value);
	});
}

function asyncChangeDisabled(targetId,value){
	$.post(url,targetId,function(result){
		$("#hoge").prop('disabled', value);
	});
}

function sendAjaxRequest(target){
	var url="NopService";
	$.post(url,target,postCallBack);
}

function postCallBack(result){
	$('#text1').val(result);
}
