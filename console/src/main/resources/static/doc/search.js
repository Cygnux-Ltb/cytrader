let api = [];
api.push({
    alias: 'api',
    order: '1',
    desc: '',
    link: '',
    list: []
})
api[0].list.push({
    order: '1',
    desc: '返回全部Strategy',
});
api[0].list.push({
    order: '2',
    desc: '使用StrategyId作为get params访问Strategy',
});
api[0].list.push({
    order: '3',
    desc: '使用StrategyId作为URI访问Param',
});
api[0].list.push({
    order: '4',
    desc: 'Put StrategyParam URI is StrategyId',
});
api.push({
    alias: 'BarController',
    order: '2',
    desc: '',
    link: '',
    list: []
})
api[1].list.push({
    order: '1',
    desc: 'Get Bars',
});
api[1].list.push({
    order: '2',
    desc: 'Put Bar',
});
api.push({
    alias: 'PnlController',
    order: '3',
    desc: '',
    link: '',
    list: []
})
api[2].list.push({
    order: '1',
    desc: '查询PNL',
});
api[2].list.push({
    order: '2',
    desc: 'Put PnlDaily',
});
api[2].list.push({
    order: '3',
    desc: '查询结算PNL',
});
api.push({
    alias: 'CommandController',
    order: '4',
    desc: '',
    link: '',
    list: []
})
api[3].list.push({
    order: '1',
    desc: '',
});
api[3].list.push({
    order: '2',
    desc: '',
});
api.push({
    alias: 'ProductController',
    order: '5',
    desc: '',
    link: '',
    list: []
})
api[4].list.push({
    order: '1',
    desc: 'Get all product',
});
api[4].list.push({
    order: '2',
    desc: '',
});
api[4].list.push({
    order: '3',
    desc: '',
});
api.push({
    alias: 'StatusController',
    order: '6',
    desc: '',
    link: '',
    list: []
})
api[5].list.push({
    order: '1',
    desc: '',
});
api[5].list.push({
    order: '2',
    desc: '',
});
api[5].list.push({
    order: '3',
    desc: '',
});
api.push({
    alias: 'InstrumentController',
    order: '7',
    desc: '',
    link: '',
    list: []
})
api[6].list.push({
    order: '1',
    desc: 'Get Settlement Price',
});
api[6].list.push({
    order: '2',
    desc: 'Get LastPrices',
});
api[6].list.push({
    order: '3',
    desc: 'Put LastPrice',
});
api[6].list.push({
    order: '4',
    desc: 'Get [TradableInstrument] for [symbol] and [tradingDay]',
});
api.push({
    alias: 'OrderController',
    order: '8',
    desc: '',
    link: '',
    list: []
})
api[7].list.push({
    order: '1',
    desc: '查询Order',
});
api[7].list.push({
    order: '2',
    desc: '',
});
api[7].list.push({
    order: '3',
    desc: '',
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