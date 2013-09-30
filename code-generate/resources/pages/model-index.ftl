<!--${"<#include \"../commons.ftl\">"}-->
${"<#assign path=\"" + domain?uncap_first + "\"/>"}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="${"$" + "{adminPath}"}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>列表</title>
<link rel="stylesheet" type="text/css" href="css/datagrid.css" media="screen" />
<script src="../js/lang/jquery.min.js"></script>
<script>
var pageSize = ${"$" + "{(pageWrapper.pageSize)!10}"}
var page = ${"$" + "{(pageWrapper.pageIndex)!1}"};
var rowCount = ${"$" + "{(pageWrapper.rowCount)!0}"};
var pageCount = ${"$" + "{(pageWrapper.pageCount)!1}"};
function update(form) {
	var o = form.elements;
	var id = '#${"$" + "{path}"}_' + o['model.id'].value;
	var row = $(id);
	if (row.length == 0) {
		if ($('tr').length >= 10) {
			// 大于10行，直接 更新最后一行
			//row = $('tr').last();
			$('tr').last().remove();
		}
		// 动态添加一行
		var h = '<tr>' + 
		    '<td><input type="checkbox" name="CheckboxGroup1" value="复选框" id="CheckboxGroup1_0" /></td>' + 
		    '<td></td>' + 
		    '<td><a href="" target="mainFrame"></a></td>' + 
		    '<td></td>' + 
		    '<td></td>' + 
		    '<td></td>' + 
		    '<td></td>' + 
		    '<td></td>' + 
		    '<td></td>' + 
		    '</tr>';
		row = $(h);		    
		$('#theader').after(row);
		
		
		// 控制上下页按钮
		if (page > 1 && $('.prev').length == 0) {
			$('.prev').before('<span class="next"><a href="${"$" + "{path}"}/?page=' + (page - 1) + '">上页</a></span>');
		}
		if (pageCount > page && $('.last').length == 0) {
			$('.last').before('<span class="next"><a href="${"$" + "{path}"}/?page=' + (page + 1) + '">下页</a></span>');
		}
		
		// 更新记录数
		rowCount++;
		pageCount = (rowCount / pageSize)%10 > 0 ? (rowCount / pageSize) + 1 : (rowCount / pageSize);
		pageCount = (pageCount < 1 ? 1 : pageCount) + "";
		pageCount = pageCount.substr(0, pageCount.indexOf('.')) * 1;
		 
		$('.count').html('当前为' + page + '页 共有' + pageCount + '页 ' + rowCount + '条记录');
		
		console.log('add ${"$" + "{path}"} ' + id + ' to list');
	} else {
		console.log('update ${"$" + "{path}"} ' + id);
	}
	console.log(o);
	updateRow(row, o);
}

function updateRow(row, o) {
	var trs = row.children();
	row.attr('id', '${"$" + "{path}"}_' + o['model.id'].value);
	trs.eq(1).html(o['model.id'].value)
	trs.eq(2).html('<a href="${"$" + "{path}"}/view.html?id=' + o['model.id'].value + '&distCode=' + o['distCode'].value + '" target="mainFrame">' + o['model.name'].value + '</a>');
	trs.eq(3).html(o['model.star'].value);
	trs.eq(4).html(o['distName'].value);
	trs.eq(5).html(o['model.mobile'].value);
	trs.eq(6).html(o['model.startDate'].value);
	trs.eq(7).html(o['model.status'].value==0?'下架':'上架');
	trs.eq(8).html(o['model.address'].value);
}

</script>
</head>

<body style="background:#f1f1f1">
<div class="list">
<div class="crump">管理后台 >产品管理</div>
<div class="listbox">
    
    <div class="listbox-tools">
        <a href="${"$" + "{path}"}/form.html" target="mainFrame" class="btn-add">+ 新增</a>
        <div class="listbox-search"></div>    
    </div>
    <div class="listbox-info">
        <table cellpadding="0" cellspacing="0" border="0" class="listbox-info-table">
        <tr id="theader">
        <th width="24"><input name="" type="checkbox" value="" /></th>
        <th>酒店编号</th>
        <th>酒店名称</th>
        <th>星级</th>
        <th>城市</th>
        <th>预定电话</th>
        <th>开业时间</th>
        <th>状态</th>
        <th>地址</th>
        </tr>
        ${"<#assign list = pageWrapper.list>"}
        ${"<#list list as model>"}
	    	<tr id="${"$" + "{path}"}_${"$" + "{(model.id)!}"}">
	        <td><input type="checkbox" name="CheckboxGroup1" value="复选框" id="CheckboxGroup1_0" /></td>
	        <td>${"$" + "{(model.id)!}"}</td>
	        <td><a href="${"$" + "{path}"}/view.html?id=${"$" + "{(model.id)!}"}&distCode=${"$" + "{(model.distCode.distCode)!}"}" target="mainFrame">${"$" + "{(model.name)!}"}</a></td>
	        <td>${"$" + "{(model.star)!}"}</td>
	        <td>${"$" + "{(model.distCode.distName)!}"}</td>
	        <td>${"$" + "{(model.mobile)!}"}</td>
	        <td>${"$" + "{(model.startDate)!}"}</td>
	        <td>${"$" + "{(((model.status)!'0')=='0')?string('下架', '上架')}"}</td>
	        <td>${"$" + "{(model.address)!}"}</td>
	        </tr>
        ${"</#list>"}
        </table>
        <div class="table-foot">
        	<p><input type="checkbox" name="CheckboxGroup1" value="复选框" id="CheckboxGroup1_0" /><span class="all">全选</span>|<span class="del">删除</span>|<span class="other">其他操作</span></p>
        	<div class="listbox-page">
            	<span class="count">当前为${"$" + "{pageWrapper.pageIndex}"}页 共有${"$" + "{pageWrapper.pageCount}"}页 ${"$" + "{pageWrapper.rowCount}"}条记录</span>
                <span class="first"><a href="${"$" + "{path}"}/">首页</a></span>
                ${"<#if !pageWrapper.firstPage>"}
                <span class="prev"><a href="${"$" + "{path}"}/?page=${"$" + "{pageWrapper.pageIndex - 1}"}">上页</a></span>
                ${"</#if>"}
                ${"<#if !pageWrapper.lastPage>"}
                <span class="next"><a href="${"$" + "{path}"}/?page=${"$" + "{pageWrapper.pageIndex + 1}"}">下页</a></span>
                ${"</#if>"}
                <span class="last"><a href="${"$" + "{path}"}/?page=${"$" + "{pageWrapper.pageCount}"}">末页</a></span>
         	</div>
        </div>
        
    </div>
</div>


</div>
</body>
</html>
