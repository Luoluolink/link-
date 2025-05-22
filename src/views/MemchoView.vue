<template>
    <div class="memchos-page">
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

        <!-- MEM啾介绍 -->
        <div class="text-section">
           <h1 @click="addToFans('Mem啾')">Mem啾</h1>
            <p>——十八岁（？）的女子高中生博主</p>
        </div>

        <!-- 展示图片 -->
        <section class="image-gallery">
            <img class="left" src="/img/memcho/memcho1.png" alt="MEM1">
            <img class="right" src="/img/memcho/memcho2.png" alt="MEM2">
            <img class="left" src="/img/memcho/memcho3.png" alt="MEM3">
            <img class="right" src="/img/memcho/memcho4.png" alt="MEM4">
            <img class="left" src="/img/memcho/memcho5.png" alt="MEM5">
        </section>

        <!-- 底部信息 -->
        <footer class="footer">
            <p>@2025 B小町展示项目</p>
            <p>@LUOLUOLINK</p>
        </footer>
    </div>
</template>
<script>
import { nextTick } from 'vue';
import axios from "axios";
export default {
    mounted() {
        nextTick(() => {
            // 文字动画
            const textSection = document.querySelector('.text-section');
            if (textSection) {
                textSection.classList.remove('show');
                setTimeout(() => {
                    textSection.classList.add('show');
                }, 50);
            }

            // **仅在“MEM”页面启动流星动画**
            if (window.location.pathname === "/memcho") {
                startMeteors();
            }
        });
    },
    beforeRouteLeave(to, from, next) {
        if (from.path === "/memcho") {
            clearMeteors(); // 离开mem页面时删除流星
        }
        next(); // 继续路由跳转
    },
     methods: {
    async addToFans(fanName) {
      const user = JSON.parse(localStorage.getItem("user"));
      if (!user) {
        alert("请先登录！");
        return;
      }

      const fanTable = this.getFanTableName(fanName); 
      if (!fanTable) {
        alert("无法识别粉丝表！");
        return;
      }

      try {
        console.log(`🚀 发送请求: 用户 ${user.username} 加入 ${fanTable}`);
        
        const response = await axios.post("http://localhost:8080/api/addFan", {
          username: user.username,
          fanTable: fanTable,
        });

        if (response.status === 200) {
          alert(`🎉 成功加入 ${fanTable} 粉丝列表！`);
          console.log(`✅ 加入成功: ${response.data}`);
        } else {
          alert(`⚠️ 加入失败: ${response.data}`);
        }
      } catch (error) {
        console.error("❌ 服务器错误:", error.response?.data || error.message);
        alert("❌ 加入失败，请稍后重试");
      }
    },
    getFanTableName(name) {
      const mapping = {
        "有马加奈": "kanafans",
        "星野露比": "rubyfans",
        "Mem啾": "memchofans",
      };
      return mapping[name] || null;
    },
  },
  
mounted() {
  nextTick(() => {
    const textSection = document.querySelector(".text-section");
    if (textSection) {
      textSection.classList.remove("show");
      setTimeout(() => {
        textSection.classList.add("show");
      }, 50);
    }

    if (window.location.pathname.includes("ruby")) {
      startMeteors();
    }

  });
},
};

// **清除所有流星**
function clearMeteors() {
    document.querySelectorAll(".star").forEach(meteor => meteor.remove());
}

// **生成流星**
function createMeteor() {
    const meteor = document.createElement("div");
    meteor.className = "star star-memcho";
    document.body.appendChild(meteor);

    // **随机决定流星从右侧还是顶部生成**
    const spawnFromRight = Math.random() < 0.5; // 50%概率从右侧生成，50%概率从顶部生成

    if (spawnFromRight) {
        // **流星从右侧开始**
        meteor.style.top = Math.random() * window.innerHeight + "px"; // 覆盖整个高度
         meteor.style.left = window.innerWidth - Math.random() * (window.innerWidth * 0.95) + "px"; // 让流星覆盖整个右上角
    } else {
        // **流星从顶部开始**
        meteor.style.top = Math.random() * window.innerHeight * 0.1 + "px"; // 限制在顶部 10% 区域
          meteor.style.left = window.innerWidth - Math.random() * (window.innerWidth * 0.95) + "px"; // 让流星覆盖整个右上角
    }


    // **强制流星进入屏幕**
    meteor.style.position = "fixed";

    setTimeout(() => {
        meteor.remove();
    }, 400000); // 让流星持续更长时间
}

// **生成多颗流星**
function startMeteors() {
    for (let i = 0; i < 30; i++) {
        setTimeout(() => createMeteor(), Math.random() * 10000); // 让流星生成时间错开
    }
}
</script>
<style >
/* 页面样式 */

/* MEM啾介绍 */

/* 图片展示区 */
.image-gallery {
    display: flex;
    flex-direction: column;
    width: 40%;
    padding-top: 40px; 
    margin-top: 40px;
    padding-bottom: 80px;
    gap: 40px; 
}

.image-gallery img {
    width: 40%; 
    transition: transform 0.3s ease-in-out;
    border-radius: 20px;
}
.image-gallery img:hover{
    transform: scale(1.1);
}
.memcho-container{
    display: flex;
    height: 100vh;
}
.image-section{
    width: 60%;
    overflow-y: scroll;
    height: 100vh;
}
/* 交错图片布局 */
.image-gallery .left {
    align-self: flex-start; 
    margin-left: 50px;

}

.image-gallery .right {
    align-self: flex-end; 
    margin-right: 50px;
}

.image-gallery img:first-child {
    margin-top: 0;
}
/* 文字部分 */
.text-section{
    width: 40%;
    position: fixed;
    right: 0;
    height: 100vh;
    background: rgba(30,30,30,0.4);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    padding: 20px;
    transform: translateY(20px);
    transition: opacity 0.8s ease-in-out, transform 0.8s ease-in-out;
}

.text-section.show {
    opacity: 1;
    transform: translateY(0);
}
.text-section h1{
    font-size: 3rem;
    font-weight: bold;
    text-align: center;
    letter-spacing: 2px;
}
.memchos-page .text-section h1, .memchos-page .text-section p{
background: linear-gradient(80deg, #f0cd1d, #34c1d3);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
}
/* 底部信息 */
.footer {
    padding: 20px;
    background: rgba(0, 0, 0, 0.3);
    color: white;
}
/* 背景 */
.memchos-page {
    background-image: url('/img/memcho-back.jpg');
    background-size: cover;
    background-position: left center;
    background-repeat: no-repeat;
    min-height: 100vh;
    background-attachment: fixed;
    margin: 0;
    padding: 0;
    padding-top:20px;
}
/* 流星 */
.star {
    position: fixed;
    width: 8px;
    height: 8px;
    border-radius: 50%;
    box-shadow: 0 0 10px rgba(255, 255, 255, 1); /* 保持亮度 */
    animation: meteor 8s linear infinite; 
    z-index: 9999; /* 确保流星不会被其他元素遮挡 */
}
.star-memcho{
     background: linear-gradient(45deg,#f0cd1d, #34c1d3); /* 半透明渐变 */
     opacity: 0.5 !important; /* 让流星完全可见 */
}
/* 增加拖尾效果 */
.star-memcho::after{
    content: '';
    position: absolute;
    width: 60px;
    height: 6px;
    background: linear-gradient(90deg,#34c1d3,rgba(255,255,255,0));
    top:50%;
    left: -40px;
    transform: rotate(-45deg) translateX(45px) translateY(7px);
    opacity: 0.2;
}

@keyframes meteor {
    0% {
        transform: translate(0, 0) scale(1);
        opacity: 1;
    }
    100% {
        transform: translate(-150vw, 150vh) scale(0.8); /* 让流星横跨整个页面 */
        opacity: 0;
    }
}

</style>