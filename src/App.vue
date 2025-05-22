<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import { computed } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()

// 根据路由动态设置背景
const pageBackground = computed(() => {
    if (route.path === '/ruby') {
        return "url('/img/ruby-back.jpg')"
    } else {
        return "url('/img/home-background.jpg')"
    }
})
</script>

<template>
  <div :style="{ 
      backgroundImage: pageBackground, 
      backgroundSize: 'cover', 
      backgroundPosition: 'center',
      minHeight: '100vh' 
    }">
    <RouterView />
  </div>
</template>

