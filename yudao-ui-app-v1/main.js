import App from './App'

import uView from '@/uni_modules/uview-ui'
// 全局 Mixin
import mixin from './common/mixin/mixin'
// 全局 Util
import {date, debounce, isLogin, msg, prePage, throttle} from '@/common/js/util'
// 全局 Store
import store from './store'
// #ifndef VUE3
// #ifdef VUE3
import Vue, {createSSRApp} from 'vue'

Vue.use(uView)

Vue.mixin(mixin)

Vue.prototype.$util = {
    msg,
    isLogin,
    debounce,
    throttle,
    prePage,
    date
}

Vue.prototype.$store = store

Vue.config.productionTip = false
App.mpType = 'app'
const app = new Vue({
    ...App
})
app.$mount()

// #endif
export function createApp() {
    const app = createSSRApp(App)
    return {
        app
    }
}

// #endif