<template>
    <div class="kanas-page">
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

        <!-- 加奈介绍 -->
        <div class="text-section">
            <h1 @click="addToFans('有马加奈')">有马加奈</h1>
            <p>——十秒钟就能哭出来的天才童星</p>
        </div>

        <!-- 展示图片 -->
        <section class="image-gallery">
            <img class="left" src="/img/kana/kana1.png" alt="加奈1">
            <img class="right" src="/img/kana/kana2.png" alt="加奈2">
            <img class="left" src="/img/kana/kana3.png" alt="加奈3">
            <img class="right" src="/img/kana/kana4.png" alt="加奈4">
            <img class="left" src="/img/kana/kana5.png" alt="加奈5">
            <img class="right" src="/img/kana/kana6.png" alt="加奈6">
        </section>

        <!-- 底部信息 -->
        <footer class="footer">
            <p>@2025 B小町展示项目</p>
            <p>@LUOLUOLINK</p>
        </footer>
    </div>
</template>
<script>
import { nextTick } from "vue";
import axios from "axios";

export default {
  mounted() {
    nextTick(() => {
      // 文字动画
      const textSection = document.querySelector(".text-section");
      if (textSection) {
        textSection.classList.remove("show");
        setTimeout(() => {
          textSection.classList.add("show");
        }, 50);
      }

      // **仅在“加奈”页面启动流星动画**
      if (window.location.pathname.includes("kana")) {
        startMeteors();
      }

      // **监听点击事件**
      document.querySelectorAll(".text-section h1").forEach((title) => {
        title.addEventListener("click", () => this.addToFans(title.innerText)); // ✅ 使用 `this.addToFans`
      });
    });
  },
  beforeRouteLeave(to, from, next) {
    if (from.path.includes("kana")) {
      clearMeteors(); // 离开加奈页面时删除流星
    }
    next();
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

    if (window.location.pathname.includes("kana")) {
      startMeteors();
    }

  });
},
};

// **清除所有流星**
function clearMeteors() {
  document.querySelectorAll(".star").forEach((meteor) => meteor.remove());
}

// **生成流星**
function createMeteor() {
  const meteor = document.createElement("div");
  meteor.className = "star star-kana";
  document.body.appendChild(meteor);

  const spawnFromRight = Math.random() < 0.5;
  meteor.style.top = Math.random() * window.innerHeight + "px";
  meteor.style.left = window.innerWidth - Math.random() * (window.innerWidth * 0.95) + "px";

  setTimeout(() => {
    meteor.remove();
  }, 400000);
}

// **生成多颗流星**
function startMeteors() {
  for (let i = 0; i < 30; i++) {
    setTimeout(() => createMeteor(), Math.random() * 10000);
  }
}
</script>


<style >
/* 页面样式 */

/* 加奈介绍 */

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
.kana-container{
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
    transition: transform 0.3s ease-in-out;
}
.text-section h1:hover{
     transform: scale(1.1);
}
.kanas-page .text-section h1, .kanas-page .text-section p{
 background: linear-gradient(45deg, #e1b4de, #d22029);
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
.kanas-page {
    background-image: url('/img/kana-back.jpg');
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
    animation: meteor 10s linear infinite;
    z-index: 9999; /* 确保流星不会被其他元素遮挡 */
}
.star-kana{
      background: linear-gradient(45deg,#e1b4de, #d22029); /* 半透明渐变 */
       opacity: 0.5 !important; /* 让流星完全可见 */
}
/* 增加拖尾效果 */
.star-kana::after{
    content: '';
    position: absolute;
    width: 60px;
    height: 6px;
    background: linear-gradient(90deg,#e1b4de,rgba(255,255,255,0));
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
        opacity: 1;
    }
}
</style>