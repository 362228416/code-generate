<!--${"<#include \"../commons.ftl\">"}-->
${"<#assign path=\"" + domain?uncap_first + "\"/>"}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="${"$" + "{adminPath}"}"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>列表</title>
<link rel="stylesheet" type="text/css" href="css/datagrid.css" media="screen" />
<script type="text/javascript" src="../js/lang/jquery.min.js"></script>
<script>

<#assign id = (model.id)!0>
<#assign distCode = (model.distCode.distCode)!"">
<#assign changeStatus=(((model.status)!'0')=='0')?string('0', '1')>
var valid = true;
var id = ${"$" + "{id}"};
var status = ${"$" + "{changeStatus}"};
var distCode = '${"$" + "{distCode}"}';

function save() {
	$('.error').remove();
	var name = $('#name');
	var address = $('#address');
	var mobile = $('#mobile');
	console.log();
	if ($.trim(name.val()) == '') {
		addMsg(name, "酒店名称必须填写");
	}
	if ($.trim(address.val()) == '') {
		addMsg(address, "酒店地址必须填写");
	}
	if ($.trim(mobile.val()) == '') {
		addMsg(mobile, "预定电话必须填写");
	}
	if (valid) {
		if (id == 0) {
			// 保存
			$.post('hotel/create.html', $('#hotelForm').serialize(), function(data){
				if (data.success) {
					id = data.id;
					distCode = data.distCode;
					$('#id').val(id);
					$('#distCode').val(distCode);
					$(':button').show();
					status = 1;
					updateToList();
				}
				showMsg(data.msg);
			}, 'json');	
		} else {
			// 更新
			$.post('hotel/update.html', $('#hotelForm').serialize(), function(data){
				showMsg(data);
				updateToList();
			});
			
		}
	} 
	valid = true;
}
function preview() {
	var a = $('<a href="${"$" + "{basePath}"}hotel/' + id + '.html" target="_blank"></a>').get(0);
	var e = document.createEvent('MouseEvents');
	e.initEvent( 'click', true, true );
	a.dispatchEvent(e);
}

function staticize() {
	$.get('hotel/staticize.html', {id: id}, function(data){
		showMsg(data);
	});
}
function downup() {
	var toStatus = status == 0 ? 1 : 0;
	$.get('hotel/downup.html', {id: id, status: toStatus}, function(data){
		showMsg(data);
		status = toStatus;
		$('#status').val(status);
		updateToList();
	});
}

function addMsg(o, msg) {
	valid = false;
	o.parent().after('<p class="error">' + msg + '</p>');
}

function showMsg(msg) {
	$(".light-box").show(200, function(){$(this).css({left:"40%",top:"50%",position:"absolute","fontSize": '26px'}).html(msg)}).fadeOut(2000, function(){$(".light-box").html('')});
}

$(document).click(function(){
	$(".light-box").hide().html('');
});

$(function(){
	if (id != 0) {
		$(':button').show();
	}
});

function updateToList() {
	top.listFrame.update($('#hotelForm').get(0));
}

</script>
</head>

<body>
<div class="main">
<form action="" id="hotelForm">
<h4>产品信息</h4>
<div class="info">
<input type="hidden" id="id" name="model.id" value="${"$" + "{(model.id)!}"}"/>
<input type="hidden" id="distCode" name="distCode" value="${"$" + "{distCode}"}"/>
<input type="hidden" id="status" name="model.status" value="${"$" + "{(model.status)!1}"}"/>
<p><span>酒店名称:</span><input id="name" name="model.name" type="text" value="$${"$" + "{(model.name)!}"}"/>*必填</p>
<p><span>所在城市:</span><input id="distName" name="distName" value="${"$" + "{(model.distCode.distName)!}"}"></p>
<p><span>酒店星级:</span><select name="model.star"><option>三星</option><option>四星</option><option>五星</option><option>六星</option><option>三星以下</option></select>*必填</p>
<p><span>酒店地址:</span><input id="address" name="model.address" type="text" class="input350" value="${"$" + "{(model.address)!}"}"/>*必填</p>
<p><span>酒店坐标:</span><input id="point" name="model.point" type="text" class="input350" value="${"$" + "{(model.point)!}"}"/></p>
<p><span>预定电话:</span><input id="mobile" name="model.mobile" type="text" value="${"$" + "{(model.mobile)!}"}"/>*必填</p>
<p><span>开业时间:</span><input type="text" id="#inp1" onclick="opcal()" name="model.startDate" /><input name="date" type="hidden"/></p>
<p><span class="fl">基本简介:</span><textarea id="introduction" name="model.introduction" cols="" rows="3" class="fl">${"$" + "{(model.introduction)!}"}</textarea></p>
<p><span>交通位置:</span><a href="#">设置地图</a><input type="hidden" name="model.traffic"/></p>
<p><span class="fl">特色推荐:</span><textarea id="feature" name="model.feature" cols="" rows="2" class="fl">${"$" + "{(model.feature)!}"}</textarea></p>
<p><span>酒店图标:</span><input name="logo" type="file" /></p>
<p><span class="fl">酒店备注:</span><textarea id="remark" name="model.remark" cols="" rows="2" class="fl">${"$" + "{(model.remark)!}"}</textarea></p>
</div>

<div class="btnbar">
	<input name="" type="button" class="btn-save" value="保存修改" onclick="save()"/><input name="" type="button"  value="预览" onclick="preview()" style="display:none" /><input name="" type="button" value="生成静态文件" onclick="staticize()" style="display:none"/><input name="" type="button" value="下架/上架" onclick="downup()" style="display:none"/><#--<input name="" type="button"  value="删除" onclick="delete()" style="display:none"/>-->
</div>

<div class="light-box">
</div>

</form>
</div>

</body>
</html>
