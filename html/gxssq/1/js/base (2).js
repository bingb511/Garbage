
$(function(){
  //活动规则
   $(document).on('click','#role',function(){
    var option = {
      'id' : 'modal_role',
      'title':'游戏规则',
      'content' : '<div class="role_c"><h2>　　为庆祝 “ 快乐十分之动物总动员 ” 在我省上市4周年，回馈广大彩民的厚爱，湖南福彩官方微信特开展 “ 快乐十分之动物总动员模拟投注大赛 ” 活动。只要用手机关注 “ 湖南福彩 ” 微信公众号，参与活动就有机会获得大奖。</h2><h3>一、活动时间</h3><p>2015年11月2日至17日</p><h3>二、活动周期</h3><p>共16天，2天为一个奖组段 （ 模拟投注大赛周期 ） ，共8个奖组段 （ 模拟投注大赛周期 ） 。</p><h3>三、活动规则</h3><p>1、手机用户通过扫描湖南福彩官方微信二维码或搜索 “ 湖南福彩 ” 公众账号进行关注。<br />2、用户可通过以下两个入口之一进入活动页面：<br />　　1）用户发送 “ 动物总动员 ” 至官微，系统会自动回复活动链接，打开链接开始参加活动。<br/>　　2）在官微底部菜单栏有“新闻公告”，选择“动物总动员”，进入  活动页面。<br />3、用户进入活动页面后，先注册个人信息，注册信息包含：姓名、手机号码、联系地址，注册完毕后即可进入模拟投注系统。用户注册成功后，系统将会自动给生成 2000 个彩豆作为用户首个活动周期的虚拟投注额。<br />4、用户进入活动页面后，有 “ 动物总动员规则 ” 按钮，可查看动物总动员玩法介绍。<br />5、“ 动物总动员模拟投注大赛 ” 活动每2天为一活动周期，当一个活动周期结束后，按活动周期中用户所有中奖金额的总数进行排名，若中奖金额总数相同，则按账户剩余彩豆金额进行排名，若再出现并列名次则按参与活动成功时间先后进行排名。活动期间用户可随时查看排名信息，并可以将自己的排名分享至朋友圈。<br />6、本活动与真实 “ 快乐十分之动物总动员 ” 销售系统同步开奖，并依据我省 “ 快乐十分之动物总动员 ” 开奖号码为准，每10分钟开奖一次，每天开奖84期（9：00—23:00）。<br />7、每个活动周期结束后，所有模拟投注用户帐户虚拟投注金额清零，并于下期开奖前重新注入虚拟投注额 2000 个彩豆，继续开展下一活动周期活动。<br />8、每活动周期结束后，中奖结果将在湖南福彩网、湖南福彩官方微信上予以公布。</p><h3>四、奖项设置</h3><p>（1）每奖组段设一等奖5名（活动周期结束时中奖总金额排名前 5 获得一等奖），各奖励红米手机一台，共 40 名。<br />（2）每奖组段设二等奖 10 名（活动周期结束时中奖总金额排名前 6-15 名获得二等奖），各奖励价值 100 元手机话费，共 80 名。<br />（3）每奖组段设三等奖 15 名（活动周期结束时中奖总金额排名前 16-30 名获得三等奖），各奖励 50 元手机话费，共120名。<br />（4）每奖组段设幸运奖 20 名（从活动周期结束后中奖金额排名 31-200 的人中系统默认抽取 20 名），各奖励 30 元手机话费，共 160 名<br />（5）每奖组段设晒排名奖 25 名，共 200 名。用户将自己的参赛排名分享至朋友圈后，即可参与转盘抽奖，中奖者各奖励 20 元手机话费，每个活动周期只可获得 一次晒排名奖 。</p><h3>五、奖品兑付办法</h3><p>（一）活动结束后，省福彩中心将按照中奖结果与中奖用户取得联系，完成中奖用户信息核实，并通知中奖用户办理实物兑奖。兑奖时省中心工作人员需认真核实中奖用户信息并填写《2015年湖南福彩官方微信 “ 快乐十分之动物总动员模拟投注大赛 ” 营销活动奖品发放签收单》，以此作为中奖用户领取奖品的凭证。获得一等奖的中奖用户可直接到省中心领取奖品或邮寄奖品。<br />(二)获二、三等、幸运、晒排名奖的用户，中奖话费将于活动结束后一个星期内充值到用户注册的手机号码中。</p></div>',
      'btn_close_title' : '关闭'
    }
    Modal.modal(option);
    return false;
  }); 

     //分享
  /*$(document).on('click','#share',function(){
    $('#mscover').show();
    setTimeout(function () {
        $("#mscover").hide();
    }, 8000);
  });
  $(document).on('click','#mscover',function(){
    $(this).hide();
  });*/

  $(document).on('click','#share',function(){
    $('.zhuan').find('.default').hide();
    $('.zhuan').find('.ly-plate').show();
  });

  //转动转盘

  

});
