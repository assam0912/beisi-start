import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus'                  // 导入element-plus
import 'element-plus/dist/index.css'                    // 导入element-plus的样式

import App from './App.vue'
import router from './router'
import locale from 'element-plus/dist/locale/zh-cn.js'  // 导入中文包

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(ElementPlus, {locale})                          // 使用element-plus

app.mount('#app')
