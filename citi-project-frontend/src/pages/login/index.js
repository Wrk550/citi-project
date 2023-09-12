import React, { Component, useState,useEffect } from 'react'
import { Link ,useNavigate} from 'react-router-dom';
import './login.styl'
import axios from 'axios';


function Login(){
    const [username,setUsername]=useState('');
    const [password,setPassword]=useState('');
    const [users, setUsers] = useState([]);
    const navigate = useNavigate();
    useEffect(() => {
      // 发送异步请求获取用户表数据
      axios.get('/api/users')
        .then(response => {
          setUsers(response.data);
        })
        .catch(error => {
          console.error(error);
        });
    }, []);

     const handleSubmit = (e) => {
    e.preventDefault();

    // 查找用户
    const user = users.find((u) => u.username === username);

    // 验证逻辑
    if (!user) {
      alert('用户名不存在');
      return;
    }

    if (user.password !== password) {
      alert('密码不正确');
      return;
    }

    // 验证通过，进行登录操作
    alert('登录成功');
    // 进行跳转或其他操作...
    navigate('/home');
  };

return (
  <div className="login-container">
    <h2>Login</h2>
    <form className="login-form">
      <div className="login-form-group">
        <label htmlFor="username">用户</label>
        <input className='login-input' type="text" id="username" />
      </div>
      <div className="login-form-group">
        <label htmlFor="password">密码</label>
        <input className='login-input' type="password" id="password" />
      </div>
      <button className='login-btn' type="submit">登录</button>
    </form>
    <p>
        还没有账号，点击
        <Link to="/register">注册</Link>
      </p>
  </div>
);

}

export default Login