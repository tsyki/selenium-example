var url="NopService";

function asyncChangeValue(targetId,value){
	$.post(url,targetId,function(result){
		$("#" + targetId).val(value);
	});
}

function asyncChangeDisabled(targetId,value){
	$.post(url,targetId,function(result){
		$("#" + targetId).prop('disabled', value);
	});
}

function asyncChangeVisiblity(targetId,value){
	$.post(url,targetId,function(result){
		$("#" + targetId).css('visibility',value); 
	});
}