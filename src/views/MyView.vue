

<template>
  <div class="my-page">
    <!-- 固定顶部导航 -->
    <header class="nav-container">
      <nav>
        <RouterLink to="/">主页</RouterLink>
        <div class="nav-links">
          <RouterLink to="/auther">作者介绍</RouterLink>
          <RouterLink to="/bkomachi">B小町介绍</RouterLink>
          <RouterLink to="/ichigo">莓制作介绍</RouterLink>
          <RouterLink to="/oshi-no-ko">《我推的孩子》介绍</RouterLink>
        </div>
        <RouterLink to="/my">我的</RouterLink>
      </nav>
    </header>

    <div class="container">
      <!-- 左侧导航 -->
      <aside class="sidebar">
        <ul>
          <li :class="{ active: currentPage === '个人信息' }" @click="currentPage = '个人信息'">个人信息</li>
          <li :class="{ active: currentPage === '粉丝标签' }" @click="currentPage = '粉丝标签'">粉丝标签</li>
          <li :class="{ active: currentPage === '我的日程' }" @click="currentPage = '我的日程'">我的日程</li>
        </ul>
      </aside>

      <!-- 主要内容 -->
      <main>
        <ProfileView v-if="currentPage === '个人信息'" />
        <FanTags v-if="currentPage === '粉丝标签'" />
        <Schedule v-if="currentPage === '我的日程'" />
      </main>
    </div>
  </div>
</template>
<script setup lang="ts">
import { ref } from "vue";
import FanTags from "./FanTags.vue";
import ProfileView from "./ProfileView.vue";
import Schedule from "./Schedule.vue";

const currentPage = ref("个人信息"); // 默认进入个人信息
</script>
<style>
/* 🔝 固定顶部导航，露比的应援色 */
.nav-container {
 position: fixed;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  padding: 0 20px;
  background-color: #ff80ab;
  top: 0;
  left: 0;
  z-index: 100;
}

.nav-links {
  display: flex;
  gap: 15px;
  margin-left: 20px;
}

/* 🏗️ 调整页面布局，防止顶部遮挡 */
.container {
  display: flex;
  margin-top: 20px; /* 确保内容不会被导航栏挡住 */
}

/* 🏗️ 左侧导航优化，让它占满左侧 */
.sidebar {
  position: fixed;
  top: 30px; /* 避免被顶部导航遮挡 */
  left: 0;
  width: 220px; /* 设定宽度，占据左侧 */
  height: calc(100vh - 60px); /* 填充整个左侧，减去顶部导航高度 */
  background: #f8f9fa;
  border-right: 2px solid #ddd; /* 增加分隔感 */
  padding: 15px;
  box-shadow: 2px 0px 8px rgba(0, 0, 0, 0.1);
}
/* 🔥 高亮选中的菜单项，使用露比的应援色 */
.sidebar ul {
  list-style-type: none;
  padding: 0;
}

.sidebar li {
  padding: 12px;
  margin: 8px 0;
  border-radius: 6px;
  background: white;
  cursor: pointer;
  font-weight: bold;
  text-align: center;
  transition: all 0.3s ease-in-out;
}

.sidebar li:hover {
  background: #ff80ab; /* 露比的应援色 */
  color: white;
}

.sidebar li.active {
  background: #ff80ab; /* 露比的应援色 */
  color: white;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

/* 📌 主体内容区域 */
main {
margin-left:220px ;
padding:20px;
}
</style>

