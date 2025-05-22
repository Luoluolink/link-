
<template>
<div class="home-page">
   <!-- 顶部导航 -->
  <header class="nav-container">
 <nav class="nav-container">
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
  <!-- 主要展示区域 -->
<main class="showcase">
    <div class="member">
      <RouterLink to="/ruby">
        <img src="/img/GDiEzU-bEAAVHQy.jpg" alt="星野露比">
      </RouterLink>
      <div class="info-box ruby-bg"></div>
    </div>
    <div class="member" id="kana">
      <RouterLink to="/kana">
        <img src="/img/GDiEznJagAANFVu.jpg" alt="有马加奈">
      </RouterLink>
      <div class="info-box kana-bg"></div>
    </div>
    <div class="member" id="memcho">
      <RouterLink to="/memcho">
        <img src="/img/GDiEz3HaYAASTEf.jpg" alt="MEM啾">
      </RouterLink>
      <div class="info-box memcho-bg"></div>
    </div>
</main>

   <!-- 底部信息 -->
    <footer class="footer">
      <p>@2025 B小町展示项目</p>
      <p>@LUOLUOLINK</p>
    </footer>
  <!-- 背景 -->
  <div class="background-overlay"></div>
</div>
</template>
<script setup>
  import { RouterLink } from "vue-router";
  import'../assets/base.css';
</script>
<!-- 样式 -->
<style>
/* 导航栏样式 */
.nav-container{
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
.nav-links{
  display: flex;
  gap:20px;
}
nav{
  display: flex;
  justify-content: center;
  padding: 0 40px;
}
nav a{
  color:white;
  text-decoration: none;
  font-size: 18px;
  transition: transform 0.3s ease-in-out;
  margin: 0 15px;
}
nav a:hover{
  transform: scale(1.08);
}
/* 主要展示区域 */
.showcase{
  padding-top: 80px;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 150px;
  width:90%;
  max-width: 1200px;
  margin: 0 auto;
  min-height: calc(100vh-150px);
}
.member{
  display: flex;
  flex-direction: column;
  text-align:center;
  position:relative;
}
.member img{
  width: auto;
  max-height: 80vh;
  /* 轻微阴影 */
  box-shadow: 5px 5px 10px rgba(0,0,0,0.4);
  /* 动画 */
  transition: transform 0.3s ease-in-out;
  z-index: 1;
  border-radius: 20px;
}
.member img:hover{
  /* 图片放大5% */
  transform: scale(1.05);
  /* 阴影增强 */
  box-shadow: 8px,8px,15px rgba(0,0,0,0.6);
}
body,html{
  height: 100%;
}
/* 底部方框样式 */
.info-box{
  position:absolute;
  top:0;
  left:0;
  width: 100%;
  height: 100%;
  padding: 20px;
  margin-right: 10px;
  margin-bottom: 10px;
  background-color: rgba(red, green, blue, alpha);
  border-radius: 8px;
  transform: translate(30px,30px);
  z-index: -1;
}
/* 背景颜色 */
.ruby-bg{
  background-color: #ffcc66;
}
.kana-bg{
  background-color: #d41a3c;
}
.memcho-bg{
  background-color: #43b0f1;
}

/* 底部信息 */
.footer{
position: fixed;
bottom:0;
left:0;
width: 100%;
background: rgba(0,0,0,0.3);
text-align: center;
padding:20px;
color:#d3d3d3;
}
/* 背景 */
/* 移除 `body{}` 里的背景，避免全局影响 */
body, html {
    margin: 0;
    padding: 0;
    width: 100%;
    overflow-x: hidden;
    
}

/* 主页背景，仅限于 HomeView.vue */
.home-page {
    background-image: url('back-ground.jpg');
    background-size: cover;
    background-position: center;
    background-attachment: fixed;
    padding-bottom: 150px;
    position: fixed;
    width: 100vw;
    min-height: 100vh; /* 让背景填满整个页面高度 */
}

/* 半透明叠加层 */
.background-overlay {
    position: absolute; /* 改为 `absolute` 让它正确定位在 `.home-page` 内部 */
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(255, 255, 255, 0.3);
    pointer-events: none; /* 确保它不会影响鼠标点击 */
    z-index: -1;
}
</style>