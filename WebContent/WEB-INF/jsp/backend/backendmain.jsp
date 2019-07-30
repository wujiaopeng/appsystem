<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/head.jsp"%>
<div class="location" style="margin-bottom: 30px">
    <strong>你现在所在的位置是:</strong>
    <span>主页</span>
</div>

<div class="row" style="margin-bottom: 10px">

    <div class="col-md-4 col-sm-6 col-xs-12 widget_tally_box" style="width: 100%">
        <div class="x_panel">
            <div class="x_title">
                <h2>下载榜</h2>
                <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li>
                    </li>
                    <li><a class="close-link"><i class="fa fa-close"></i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">

                <div class="col-xs-12 bg-white progress_summary">

                    <div class="row">
                        <c:forEach var="appInfo" items="${allAppList}">
                        <div class="progress_title">

                            <div class="clearfix"></div>
                        </div>

                        <div class="col-xs-2" style="padding-top: 20px">
                            <span>${appInfo.softwareName}</span>
                        </div>
                        <div class="col-xs-8" style="padding-top: 20px">
                            <div class="progress progress_sm">
                                <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="80" style="width: ${appInfo.downloads/100}%;" aria-valuenow="88"></div>
                            </div>
                        </div>
                        <div class="col-xs-2 more_info" style="padding-top: 20px">
                            <span>${appInfo.downloads/100}%(${appInfo.downloads})</span>
                        </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>
        <div class="row">
            <div class="col-md-4">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>应用榜<small>Application List</small></h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <c:forEach var="application" items="${applicationList}">
                            <article class="media event" style="padding-top: 20px">
                                <a class="pull-left image view view-first"  style="width: 73px">
                                    <img src="${application.logoPicPath}" style="height: 73px;width: 73px ; display: block;" alt="image"/>
                                </a>
                                <div class="media-body mask" style="padding-left: 20px">
                                    <a class="title" href="#">${application.softwareName}</a>
                                    <p>总下载<small>${application.downloads}</small>次</p>
                                    <p><small>${application.categoryLevel1Name } -> ${application.categoryLevel2Name } -> ${application.categoryLevel3Name } </small></p>
                                    <p> ${application.softwareSize}M</p>
                                </div>
                            </article>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>游戏榜<small>Game list</small></h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <c:forEach var="game" items="${allGameList}">
                            <article class="media event" style="padding-top: 20px">
                                <a class="pull-left image view view-first"  style="width: 73px">
                                    <img src="${game.logoPicPath}" style="height: 73px;width: 73px ; display: block;" alt="image"/>
                                </a>
                                <div class="media-body mask" style="padding-left: 20px">
                                    <a class="title" href="#">${game.softwareName}</a>
                                    <p>总下载<small>${game.downloads}</small>次</p>
                                    <p><small>${game.categoryLevel1Name } -> ${game.categoryLevel2Name } -> ${game.categoryLevel3Name } </small></p>
                                    <p>${game.softwareSize}M</p>
                                </div>
                            </article>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>总榜<small>General list</small></h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content" >
                        <c:forEach var="appInfo" items="${allAppList}">
                            <article class="media event" style="padding-top: 20px">
                                <a class="pull-left image view view-first" style="width: 73px">
                                    <img src="${appInfo.logoPicPath}" style="height: 73px;width: 73px ; display: block;" alt="image"/>
                                </a>
                                <div class="media-body mask" style="padding-left: 20px">
                                    <a class="title" href="#">${appInfo.softwareName}</a>
                                    <p>总下载<small>${appInfo.downloads}</small>次</p>
                                    <p><small>${appInfo.categoryLevel1Name } -> ${appInfo.categoryLevel2Name } -> ${appInfo.categoryLevel3Name } </small></p>
                                    <p> ${appInfo.softwareSize}M</p>
                                </div>
                            </article>
                        </c:forEach>
                    </div>
                </div>
            </div>

        </div>
    </div>

<%@include file="common/footer.jsp"%>