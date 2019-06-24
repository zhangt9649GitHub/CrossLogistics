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
        ,url:'/area/getallareaname'
        // ,data:data
        ,cellMinWidth: 100
        ,idField:'chinaAreaId'//必須字段
        ,treeId:'chinaAreaId'//树形id字段名称
        ,treeUpId:'chinaAreaParentId'//树形父id字段名称
        ,treeShowName:'chinaAreaName'//以树形式显示的字段
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
            ,{field:'chinaAreaName',  title: '名称'}
            ,{field:'chinaAreaId',width:100, title: 'Id'}
            ,{field:'chinaAreaParentId',width:100, title: 'pId'}

        ]]
        ,parseData:function (res) {//数据加载后回调
            return res;
        }
        ,onClickRow:function (index, o) {
            // console.log(index,o,"单击！");
        }
        ,onDblClickRow:function (index, o) {
            // console.log(index,o,"双击");
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
            axios.post('/area/delchinaarea',{'chinaAreaId':id})
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

    zq_admin_show('编辑','/admin/domesticAddress/edit?chinaAreaId='+ obj.data.chinaAreaId,'400','500');
}


