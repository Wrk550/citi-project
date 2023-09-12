import React, { useState, Fragment } from 'react';
import { Link } from 'react-router-dom';
import './product.styl'
function Product (){
  const [clientProduct,setclientProduct]=useState([]);
  const [clientName, setclientName] = useState('');
  const [productName, setproductName] = useState('');
  const [ric, setric] = useState('');
  const [ticker, setticker] = useState('');

  //获取表单数据
  const handleClientNameChange = (event) => {
    setclientName(event.target.value);  
  };
  const handleProductNameChange = (event) => {  
    setproductName(event.target.value);  
  };
  const handleRicChange = (event) => {
    setric(event.target.value);
  };
  const handleTickerChange = (event) => {
    setticker(event.target.value);
  };

  //获取客户持有对应股票数量
 
  const fetchClientProduct = async () => {
    setclientProduct([...clientProduct,clientName]);
    setclientName('');
    setproductName('');
    setric('');
    setticker('');
    try {
      const response = await fetch(`/own/client/list/${clientName,productName,ric,ticker}`);
      const data = await response.json();
      // setclientProduct(data);
      // setclientName('');
      // setproductName('');
      // setric('');
      // setticker('');
    } catch (error) {
      console.log(error);
    }
  };
    return(
<Fragment>
<div className="product-container">

<input type="text" value={clientName} onChange={handleClientNameChange} placeholder="clientName" />
<input type="text" value={productName} onChange={handleProductNameChange} placeholder="productName" />
<input type="text" value={ric} onChange={handleRicChange} placeholder="ric" />
<input type="text" value={ticker} onChange={handleTickerChange} placeholder="ticker" />
<button className='check-btn' onClick={fetchClientProduct}>Search</button>
 <h2>Client_Product Table</h2>
<div>
{clientProduct.length > 0 && (
        <table>
          <thead>
            <tr>
            <th>Client Id</th>
            <th>Client Name</th>
            <th>Product Id</th>
            <th>Product Name</th>
            <th>Ticker</th>
            <th>Ric</th>
            <th>Size</th>
            </tr>
          </thead>
          <tbody>
            {clientProduct.map((item) => (
              <tr key={item.id}>
                <td>{item.clientId}</td>
                <td>{item.clientName}</td>
                <td>{item.productId}</td>
                <td>{item.productName}</td>
                <td>{item.ticker}</td>
                <td>{item.ric}</td>
                <td>{item.size}</td>
              </tr>
            ))}    
          </tbody>
        </table>
        )}
        </div>
    <div className='product-button'>
      <button className='product-btn'><Link to="/enter">返回</Link></button>
      <button className='product-btn'><Link to="/home">进行交易</Link></button>
  </div>
  </div>
</Fragment>
    );
};

export default Product;
