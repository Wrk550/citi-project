import React from 'react'
import {Link} from 'react-router-dom';
import './enter.styl'
import logo from'../image/citi.jpeg'

function Enter(){
return (
  
  <div className='enter'>
     <div className="left">
            <div class="welcome">Welcome to</div>
            <div class="icon"><img src={logo}/></div>
      </div>
  <div className="enter-container">
    <h2>Citi Trade System</h2>
      <button className='enter-btn'><Link to="/client">查看客户信息</Link></button>
      <button className='enter-btn'><Link to="/product">查看客户股票</Link></button>
      <button className='enter-btn'><Link to="/stock">查看股票信息</Link></button>
      <button className='enter-btn'><Link to="/home">进入交易界面</Link></button>
  </div>
      </div>
  
);

}

export default Enter