<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/ManifestManagement/open.js"></script>

</head>
<body>

	<div class="zq-body">
        <input type="hidden" value="${formId}" name="formId">
		<input type="hidden" value="${type}" name="type">
		<div class="zq-main">

			<div class="title">货物清单</div>
			<hr />
			<div class="content">
				<div class="table">
                    <table id="idTest" lay-filter="demo"></table>
				</div>
			</div>
		</div>

	</div>
	<script type="text/html" id="isArrivalPay">
		{{# if(d.isArrivalPay == 1){ }}
			否

		{{# } }}

		{{# if(d.isArrivalPay == 2){ }}
			是

		{{# } }}

	</script>
</body>
</html>