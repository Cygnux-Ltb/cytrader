let api = [];
api.push({
    alias: 'api',
    order: '1',
    desc: '用户服务',
    link: '用户服务',
    list: []
})
api[0].list.push({
    order: '1',
    desc: '用户登陆',
});
api[0].list.push({
    order: '2',
    desc: '用户注册, 当前不支持新用户注册',
});
api.push({
    alias: 'StrategyController',
    order: '2',
    desc: '策略服务',
    link: '策略服务',
    list: []
})
api[1].list.push({
    order: '1',
    desc: '返回全部Strategy',
});
api[1].list.push({
    order: '2',
    desc: '使用StrategyId作为get params访问Strategy',
});
api[1].list.push({
    order: '3',
    desc: '使用StrategyId作为URI访问Param',
});
api[1].list.push({
    order: '4',
    desc: 'Put StrategyParam URI is StrategyId',
});
api.push({
    alias: 'BarController',
    order: '3',
    desc: '历史行情接口',
    link: '历史行情接口',
    list: []
})
api[2].list.push({
    order: '1',
    desc: '获取1分钟Bar',
});
api[2].list.push({
    order: '2',
    desc: 'Put Bar',
});
api.push({
    alias: 'PnlController',
    order: '4',
    desc: 'PNL服务接口',
    link: 'pnl服务接口',
    list: []
})
api[3].list.push({
    order: '1',
    desc: '查询PNL',
});
api[3].list.push({
    order: '2',
    desc: 'Put PnlDaily',
});
api[3].list.push({
    order: '3',
    desc: '查询结算PNL',
});
api.push({
    alias: 'CommandController',
    order: '5',
    desc: '系统指令服务',
    link: '系统指令服务',
    list: []
})
api[4].list.push({
    order: '1',
    desc: '更新参数',
});
api[4].list.push({
    order: '2',
    desc: '安全更新参数',
});
api.push({
    alias: 'ProductController',
    order: '6',
    desc: '产品服务接口',
    link: '产品服务接口',
    list: []
})
api[5].list.push({
    order: '1',
    desc: '获取全部产品',
});
api[5].list.push({
    order: '2',
    desc: '获取指定产品信息',
});
api[5].list.push({
    order: '3',
    desc: '产品初始化',
});
api.push({
    alias: 'StatusController',
    order: '7',
    desc: '交易系统状态服务接口',
    link: '交易系统状态服务接口',
    list: []
})
api[6].list.push({
    order: '1',
    desc: '获取全部策略状态',
});
api[6].list.push({
    order: '2',
    desc: '发送状态指令',
});
api[6].list.push({
    order: '3',
    desc: '更新状态',
});
api.push({
    alias: 'PositionController',
    order: '8',
    desc: '',
    link: '',
    list: []
})
api.push({
    alias: 'InstrumentController',
    order: '9',
    desc: '交易标的查询接口',
    link: '交易标的查询接口',
    list: []
})
api[8].list.push({
    order: '1',
    desc: '获取结算价格',
});
api[8].list.push({
    order: '2',
    desc: '获取最新价格',
});
api[8].list.push({
    order: '3',
    desc: '更新最新价格',
});
api[8].list.push({
    order: '4',
    desc: '获取可交易的标的',
});
api.push({
    alias: 'OrderController',
    order: '10',
    desc: '订单服务接口',
    link: '订单服务接口',
    list: []
})
api[9].list.push({
    order: '1',
    desc: '查询Order',
});
api[9].list.push({
    order: '2',
    desc: '获取订单最新状态',
});
api[9].list.push({
    order: '3',
    desc: '新增订单',
});
document.onkeydown = keyDownSearch;
function keyDownSearch(e) {
    const theEvent = e;
    const code = theEvent.keyCode || theEvent.which || theEvent.charCode;
    if (code === 13) {
        const search = document.getElementById('search');
        const searchValue = search.value;
        let searchArr = [];
        for (let i = 0; i < api.length; i++) {
            let apiData = api[i];
            const desc = apiData.desc;
            if (desc.toLocaleLowerCase().indexOf(searchValue) > -1) {
                searchArr.push({
                    order: apiData.order,
                    desc: apiData.desc,
                    link: apiData.link,
                    alias: apiData.alias,
                    list: apiData.list
                });
            } else {
                let methodList = apiData.list || [];
                let methodListTemp = [];
                for (let j = 0; j < methodList.length; j++) {
                    const methodData = methodList[j];
                    const methodDesc = methodData.desc;
                    if (methodDesc.toLocaleLowerCase().indexOf(searchValue) > -1) {
                        methodListTemp.push(methodData);
                        break;
                    }
                }
                if (methodListTemp.length > 0) {
                    const data = {
                        order: apiData.order,
                        desc: apiData.desc,
                        alias: apiData.alias,
                        link: apiData.link,
                        list: methodListTemp
                    };
                    searchArr.push(data);
                }
            }
        }
        let html;
        if (searchValue === '') {
            const liClass = "";
            const display = "display: none";
            html = buildAccordion(api,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        } else {
            const liClass = "open";
            const display = "display: block";
            html = buildAccordion(searchArr,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        }
        const Accordion = function (el, multiple) {
            this.el = el || {};
            this.multiple = multiple || false;
            const links = this.el.find('.dd');
            links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown);
        };
        Accordion.prototype.dropdown = function (e) {
            const $el = e.data.el;
            let $this = $(this), $next = $this.next();
            $next.slideToggle();
            $this.parent().toggleClass('open');
            if (!e.data.multiple) {
                $el.find('.submenu').not($next).slideUp("20").parent().removeClass('open');
            }
        };
        new Accordion($('#accordion'), false);
    }
}

function buildAccordion(apiData, liClass, display) {
    let html = "";
    if (apiData.length > 0) {
         for (let j = 0; j < apiData.length; j++) {
            html += '<li class="'+liClass+'">';
            html += '<a class="dd" href="' + apiData[j].alias + '.html#header">' + apiData[j].order + '.&nbsp;' + apiData[j].desc + '</a>';
            html += '<ul class="sectlevel2" style="'+display+'">';
            let doc = apiData[j].list;
            for (let m = 0; m < doc.length; m++) {
                html += '<li><a href="' + apiData[j].alias + '.html#_' + apiData[j].order + '_' + doc[m].order + '_' + doc[m].desc + '">' + apiData[j].order + '.' + doc[m].order + '.&nbsp;' + doc[m].desc + '</a> </li>';
            }
            html += '</ul>';
            html += '</li>';
        }
    }
    return html;
}