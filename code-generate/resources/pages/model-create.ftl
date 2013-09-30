${"<#if errorCode=1>"}
	{"success": false, "msg": "保存失败"}
${"<#else>"}
	{"success": true, "msg": "保存成功", "id": ${"$" + "{model.productId}"}, "distCode": "${"$" + "{distCode}"}"}
${"</#if>}"}