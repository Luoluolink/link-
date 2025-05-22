<template>
  <div class="login-container">
      <!-- 顶部导航 -->
     <header class="nav-login">
      <nav class="nav-login">
       <p>B小町</p>
       <RouterLink to="/register">注册</RouterLink>
      </nav>
     </header>
        <div class="login-ne">
        <h2>用户登录</h2>
        <input v-model="username" placeholder="用户名"/>
        <input v-model="password" type="password" placeholder="密码"/>
        <button @click="login">登    录</button>
        <p v-if="errorMsg">{{ errorMsg }}</p>
        </div>
    </div>
      <!-- 底部信息 -->
    <footer class="footer">
      <p>@2025 B小町展示项目</p>
      <p>@LUOLUOLINK</p>
    </footer>
    <div class="info-section">
      <div class="info-box1">
        <h3>B小町偶像团体</h3>
        <p>来自传奇偶像团体B小町，致力于传递偶像精神</p>
      </div>
      <div class="info-box2">
        <h3>🎤 成员介绍</h3>
        <p>露比 | 有马加奈 | MEM啾</p>
      </div>
      <div class="info-box3">
        <h3>📀 代表作品</h3>
        <p>《STAR☆T☆RAIN》 | 《HEART’s♡KISS》 | 《深海52Hz》</p>
      </div>
      <div class="info-box4">
        <h3>🎉 粉丝文化</h3>
        <p>应援活动 | 线下见面会 | 周边商品</p>
      </div>
      <div class="info-box5">
        <h3>📢 关注我们</h3>
        <p>微博 | Twitter | YouTube</p>
      </div>
    </div>
</template>
<script>
import axios from 'axios';

export default {
  data() {
    return {
      username: '',
      password: '',
      errorMsg: '',
    };
  },
  methods: {
    async login() {
      try {
        const response = await axios.post('http://localhost:8080/api/login', {
          username: this.username,
          password: this.password,
        },{
          headers: { 'Content-Type': 'application/json' },
        });

        if (response.status === 200 && response.data) {
          console.log("✅ 登录成功:", response.data);

          // 🔥 存储 **username** 到 `localStorage`，确保后续请求能正确使用
          if (response.data.username) {
            localStorage.setItem("username", response.data.username); // ✅ 存储用户名
          } else {
            console.error("❌ 响应数据中缺少 `username`");
            this.errorMsg = "登录失败，请稍后重试";
            return;
          }

          alert("✅ 登录成功！");
          this.$router.push('/'); // ✅ 登录成功后跳转
        } else {
          this.errorMsg = "❌ 用户名或密码错误";
        }
      } catch (error) {
        console.error("❌ 登录失败:", error);
        this.errorMsg = "❌ 登录失败，请稍后重试";
      }
    },
  },
};
</script>


<style scoped>
/* 页面背景 */
.login-container {
  width: 100%;
  margin: auto;
  min-height: 85vh;
  text-align: center;
  background-image: url('login2 (1).jpg');
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}

/* 顶部导航 */
.nav-login {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  padding: 10px 40px;
 background: white;
  font-weight: bold;
  font-size: 18px;
  color: #ff6b81;
  position: relative;
  z-index: 100;
}

.nav-login p {
  font-size: 22px;
  font-weight: bold;
  letter-spacing: 1px;
}

.nav-login a {
  color: #ff6b81;
  text-decoration: none;
  font-size: 18px;
  transition: transform 0.5s ease, color 0.5s ease;
  margin: 0 15px;
}

.nav-login a:hover {
  color: rgb(242, 179, 221);
}

/* 登录框 */
.login-ne {
  position: absolute;
  top: 50%;
  right: 100px;
  transform: translateY(-65%);
  background: rgba(255, 255, 255, 0.6);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 400px;
  height: 300px;
  padding: 30px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.3);
  border-radius: 20px;
  transition: all 0.3s ease-in-out;
}

input {
  padding: 12px;
  margin-bottom: 15px;
  width: 100%;
  border: 2px solid #ddd;
  border-radius: 5px;
}

button {
  background: #ff6b81;
  color: wheat;
  width: 300px;
  padding: 12px;
  border-radius: 10px;
  font-size: 20px;
  cursor: pointer;
  transition: background 0.3s;
}

button:hover {
  background: #e05269;
}

/* 底部说明 */
.info-section {
    position: relative; /* 确保它不会被绝对定位的元素遮挡 */
    z-index: 10; /* 让它高于某些背景元素，但低于导航 */
    display: flex;
    justify-content: center;
    gap: 30px;
    padding: 40px;
    padding-bottom: 90px;
    background: white;
    text-align: center;
    font-size: 14px;
    color: #333;
  
}





.info-box1,.info-box2,.info-box3,.info-box4,.info-box5{
    max-width: 300px;
    min-width: 250px;
    text-align: center;
}



.info-box1 h3,.info-box2 h3,.info-box3 h3,.info-box4 h3,.info-box5 h3{
  font-size: 18px;
  color: #ff6b81;
}
.footer{
position: fixed;
bottom:0;
left:0;
width: 100%;
background: rgba(0,0,0,0.3);
text-align: center;
padding:20px;
color:#d3d3d3;
z-index: 100;
}
</style>