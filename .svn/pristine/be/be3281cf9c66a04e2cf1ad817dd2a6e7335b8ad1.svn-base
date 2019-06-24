var data = [{'bizName':'一级','value':'','id':'1000','parentId':''},
    {'bizName':'一级','value':'','id':'1001','parentId':'1000'}]
var editObj=null,ptable=null,treeGrid=null,tableId='treeTable',layer=null;
layui.config({
    base: '/static/layui/extends/'
}).extend({
    treeGrid:'treeGrid'
}).use(['jquery','treeGrid','layer'], function(){
    var $=layui.jquery;
    treeGrid = layui.treeGrid;//很重要
    layer=layui.layer;
    ptable=treeGrid.render({
        id:tableId
        ,elem: '#'+tableId
        ,url:'/bizdictionary/getBizdictionaryList'
        // ,data:data
        ,cellMinWidth: 100
        ,idField:'id'//必須字段
        ,treeId:'id'//树形id字段名称
        ,treeUpId:'parentId'//树形父id字段名称
        ,treeShowName:'bizName'//以树形式显示的字段
        ,heightRemove:[".dHead",10]//不计算的高度,表格设定的是固定高度，此项不生效
        ,height:'100%'
        ,isFilter:false
        ,iconOpen:true//是否显示图标【默认显示】
        ,isOpenDefault:false//节点默认是展开还是折叠【默认展开】
        ,loading:true
        ,method:'get'
        ,isPage:false
        ,cols: [[
            {type:'numbers'}
            // ,{width:150,title: '操作', align:'center'/*toolbar: '#barDemo'*/
            //     ,templet: function(d){
            //         var html='';
            //         // var addBtn='<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="add">添加</a>';
            //         var delBtn='<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>';
            //         var editBtn='<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="edit">编辑</a>';
            //         return delBtn+editBtn;
            //     }
            // }
            ,{width:150,title: '操作', align:'center',templet:'#titleTpl'}
            ,{field:'bizName',  title: '名称'}
            ,{field:'value',  title: '信息'}
            ,{field:'id',width:100, title: 'id'}
            ,{field:'parentId',width:100, title: 'parentId'}
            // ,{field:'disorder',width:100, title: 'disorder'}

        ]]
        ,parseData:function (res) {//数据加载后回调
            return res;
        }
        ,onClickRow:function (index, o) {
            console.log(index,o,"单击！");
        }
        ,onDblClickRow:function (index, o) {
            console.log(index,o,"双击");
        }
    });

    treeGrid.on('tool('+tableId+')',function (obj) {
        if(obj.event === 'del'){//删除行
            del(obj);
        }else if(obj.event==="edit"){//编辑行
            edit(obj);
        }
    });
});

function del(id) {
    layer.confirm("你确定删除数据吗？如果存在下级节点则一并删除，此操作不能撤销！", {icon: 3, title:'提示'},
        function(index){//确定回调
            // obj.del();
            // console.log(obj);
            //按钮【确定】的回调
            axios.get('/bizdictionary/deleteBizdictionaryById?id=' +id)
                .then(function (response) {
                    // console.log(response);
                    layui.layer.msg(response.data.msg);
                    layer.close(index);
                    // response.data.msg == '删除成功' ? location.replace(location.href) : '';
                    response.data.msg == '删除成功' ? setTimeout(function () {location.replace(location.href);}, 1000) : '';

                });

        },function (index) {//取消回调
            layer.close(index);
        }
    );
}

// 编辑
function edit(obj) {

    zq_admin_show('编辑','/admin/dataDictionary/edit?id='+ obj.data.id,'400','500');
}


var i=1000000;
//添加
function add(pObj) {
    var pdata=pObj?pObj.data:null;
    var param={};
    param.name='水果'+Math.random();
    param.id=++i;
    param.pId=pdata?pdata.id:null;
    treeGrid.addRow(tableId,pdata?pdata[treeGrid.config.indexName]+1:0,param);
}

function print() {
    console.log(treeGrid.cache[tableId]);
    var loadIndex=layer.msg("对象已打印，按F12，在控制台查看！", {
        time:3000
        ,offset: 'auto'//顶部
        ,shade: 0
    });
}

function openorclose() {
    var map=treeGrid.getDataMap(tableId);
    var o= map['102'];
    treeGrid.treeNodeOpen(tableId,o,!o[treeGrid.config.cols.isOpen]);
}


function openAll() {
    var treedata=treeGrid.getDataTreeList(tableId);
    treeGrid.treeOpenAll(tableId,!treedata[0][treeGrid.config.cols.isOpen]);
}

function getCheckData() {
    var checkStatus = treeGrid.checkStatus(tableId)
        ,data = checkStatus.data;
    layer.alert(JSON.stringify(data));
}
function radioStatus() {
    var data = treeGrid.radioStatus(tableId)
    layer.alert(JSON.stringify(data));
}
function getCheckLength() {
    var checkStatus = treeGrid.checkStatus(tableId)
        ,data = checkStatus.data;
    layer.msg('选中了：'+ data.length + ' 个');
}

function reload() {
    treeGrid.reload(tableId,{
        page:{
            curr:1
        }
    });
}
function query() {
    treeGrid.query(tableId,{
        where:{
            name:'sdfsdfsdf'
        }
    });
}

function test() {
    console.log(treeGrid.cache[tableId],treeGrid.getClass(tableId));


    /*var map=treeGrid.getDataMap(tableId);
    var o= map['102'];
    o.name="更新";
    treeGrid.updateRow(tableId,o);*/
}