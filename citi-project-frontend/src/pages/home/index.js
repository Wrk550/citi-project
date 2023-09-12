import React, { useState, useEffect, Fragment } from 'react';
import axios from 'axios';
import './home.styl'
import Chart from'../chart/index.js'
function Home  ()  {
  const [formData, setFormData] = useState({ clientId:'',ticker:'',ric:'',size:'',price:'',currency:'',issueSector:'',salesperson:''});
  const [tableData, setTableData] = useState([]);
  const [date,setDate]=useState('');
  //选择查看的日期
  const [selectedOption, setselectedOption] = useState(0);
  const [unit, setUnit] = useState('');//HT/PT

  //可视化图表所需数据
  const [sumdata, setsumData] = useState([]);
  //可视化图表可见性
  const [isChartVisible, setChartVisible] = useState(false);
  const handleClick = () => {
    setChartVisible(!isChartVisible);
  };

  //table数据处理
  const [Totalbuy, setTotalbuy] = useState(0);
  const [Totalsell, setTotalsell] = useState(0);
  const [NetQuantity, setNetQuantity] = useState(0);
  
  const [NTotalbuy, setNTotalbuy] = useState(0);
  const [NTotalsell, setNTotalsell] = useState(0);
  const [NetNation, setNetNation] = useState(0);

  const [record,setRecord]=useState(0);
 
  

  //表单输入函数
  const handleInputChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };
 //表单提交函数
  const handleSubmit = async (e) => {
        e.preventDefault();
        const price = parseFloat(e.target.price.value);
        const usd0=((e.target.size.value)*(e.target.price.value));
        const usd = parseFloat(usd0);
        const buttonValue = e.nativeEvent.submitter.value; // 获取按钮的值
          
        //table数据处理 
         if(buttonValue=='buy'){
          const newBuyTotal = Totalbuy + price;
          setTotalbuy(newBuyTotal);
          const newNTotalbuy = NTotalbuy + usd;
          setNTotalbuy(newNTotalbuy);

          setNetQuantity(Totalbuy -Totalsell);
          setNetNation(NTotalbuy - NTotalsell);

         }else{
          const newSellTotal = Totalsell + price;
          setTotalsell(newSellTotal);

          const newNTotalsell = NTotalsell + usd;
          setNTotalsell(newNTotalsell);

          setNetQuantity(Totalbuy -Totalsell);
          setNetNation(NTotalbuy - NTotalsell);
        

         }
         
         setsumData([...sumdata,Totalbuy,Totalsell,NetQuantity])
         
          //获取系统日期
         const currentDate=new Date();
         const year = currentDate.getFullYear();
         const month = String(currentDate.getMonth() + 1).padStart(2, '0');
         const day = String(currentDate.getDate()).padStart(2, '0');
         const formattedDate = `${year}-${month}-${day}`;
         setDate(formattedDate);

        //提交表单后表格显示所需数据
         const data = {
          date,
          clientId:formData.clientId,
          clientSide:buttonValue,
          ticker:formData.ticker,
          ric:formData.ric,
          size:formData.size,
          price:formData.price,
          notionalUsd:((formData.size)*(formData.price)),
          currency:formData.currency,
          issueSector:formData.issueSector,
          salesperson:formData.salesperson,
          htPt:unit  
        };
        
        //表单提交到表格
        setTableData([...tableData, data]);
        setRecord(record+1);

        //表单提交后输入框置空
         setFormData({ clientId:'',ticker:'',ric:'',size:'',price:'',currency:'',issueSector:'',salesperson:'',buttonValue:'',date:'' });
         setUnit('');  

    //  post交易数据
    const requestBody ={
      clientId:data.clientId,
      currency:data.currency,
      htPt:data.htPt,
      ric:data.ric,
      size:data.size,
      ticker:data.ticker
    };
    if(buttonValue=='buy'){   
    try {
      await axios.post('http://114.132.91.6:7000/trade/info/trade/buy', requestBody);
      setTableData([...tableData, data]); 
       setRecord(record+1);
    } catch (error) {
      console.error(error);
    }
  }else{
    try {
      await axios.post('/info/trade/sell', requestBody);
      // setTableData([...tableData, data]); 
      // setRecord(record+1);
    } catch (error) {
      console.error(error);
    }
  }
 

    
  };
  
//按日期查询处理
  const handleOptionChange = (e) => {
    setselectedOption(parseInt(e.target.value));
  };

  

  const handleQuery = async () => {
    try {
      const response = await fetch('http://114.132.91.6:7000/trade/info/trade/list', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ timePeriod: selectedOption })
      });

      const data = await response.json();
      setTableData(data);
    } catch (error) {
      console.error('Error:', error);
    }
  };

  
        // //chart所需数据
        //   useEffect(() => {
        //     // 发送GET请求
        //     axios.get('/ledger/trade/list')
        //       .then(response => {
        //         // 处理响应数据
        //         setsumData(response.data);
        //       })
        //       .catch(error => {
        //         // 处理错误
        //         console.error(error);
        //       });
        //   }, []);


  
  



  return (
    <Fragment>
    <div className='trade-type'>
    <button  className='tradtional' type="submit">Traditional Trade</button>
      <button className='NLP' type="submit">NLP Trade</button>
    </div>

    <form onSubmit={handleSubmit}>
    <div className='submit_form'>

      <div className="form-group">
        <label htmlFor="hclientId">Client Id</label>
        <input 
        type="text"
        name="clientId"
        value={formData.clientId}
        placeholder="Text input"
        onChange={handleInputChange}
        id="hclientId"
      />
      </div>
      
      <div className="form-group">
        <label htmlFor="hticker">Ticker</label>
        <input
       type="text"
       name="ticker"
       value={formData.ticker}
       placeholder="Text input"
       onChange={handleInputChange}
        id="hticker"
      />
      </div>

      <div className="form-group">
        <label htmlFor="hric">RIC</label>
        <input
       type="text"
       name="ric"
       value={formData.ric}
       placeholder="Text input"
       onChange={handleInputChange}
        id="hricr"
      />
      </div>
      
      <div className="form-group">
        <label htmlFor="hsize">Size</label>
        <input
       type="number"
       name="size"
       value={parseFloat(formData.size)}
       placeholder="Text input"
       onChange={handleInputChange}
        id="hsize"
      />
      </div>
      
      <div className="form-group">
        <label htmlFor="hprice">Price</label>
        <input
        type="number"
        name="price"
        value={parseFloat(formData.price)}
        placeholder="Text input"
        onChange={handleInputChange}
        id="hprice"
      />
      </div>
            
      <div className="form-group">
        <label htmlFor="hcurrency">Currency</label>
        <input
        type="text"
        name="currency"
        value={formData.currency}
        placeholder="Text input"
        onChange={handleInputChange}
        id="hcurrency"
      />
      </div>
            
      <div className="form-group">
        <label htmlFor="hsector">Issuer Sector</label>
        <input
        type="text"
        name="issueSector"
        value={formData.issueSector}
        placeholder="Text input"
        onChange={handleInputChange}
        id="hsector"
      />
      </div>     

      <div className="form-group">
        <label htmlFor="hsalesperson">Salesperson</label>
        <input
          type="text"
          name="salesperson"
          value={formData.salesperson}
          placeholder="Text input"
          onChange={handleInputChange}
        id="hsalesperson"
      />
      </div>  

      <div className="form-group">
        <label htmlFor="hht">HT/PT</label>
      <select   value={unit} onChange={e => setUnit(e.target.value)}>
        <option value="">HT/PT</option>
        <option value="HT">HT</option>
        <option value="PT">PT</option> 
      </select>
      </div> 
      <button  className='b-buy' type="submit" value={"buy"}>Buy</button>
      <button className='b-sell' type="submit" value={'sell'}>Sell</button>
      </div>
      
     
      


    </form>

    <div className='search' >  
    
    <label>
          <input
            type="radio"
            value="1"
            checked={selectedOption ===1}
            onChange={handleOptionChange}
          />
          1D
        </label>
        <label>
          <input
            type="radio"
            value="2"
            checked={selectedOption ===2}
            onChange={handleOptionChange}
          />
          1W
        </label>
        <label>
          <input
            type="radio"
            value="3"
            onChange={handleOptionChange}
            checked={selectedOption ===3}
          />
          2W
        </label>
        <label>
          <input
            type="radio"
            value="4"
            onChange={handleOptionChange}
            checked={selectedOption ===4}
            
          />
          1M
        </label>
        <label>
          <input
            type="radio"
            value="5"
            checked={selectedOption ===5}
            onChange={handleOptionChange}
          />
          3M
        </label>
        <label>
          <input
            type="radio"
            value="6"
            checked={selectedOption ===6}
            onChange={handleOptionChange}
          />
          6M
        </label>
        <label>
          <input
            type="radio"
            value="7"
            checked={selectedOption ===7}
            onChange={handleOptionChange}
          />
          1Y
        </label>
        <label>
          <input
            type="radio"
            value="8"
            checked={selectedOption === 8}
            onChange={handleOptionChange}
          />
          YTD
        </label>
      

      <button className='check-btn' type="button" onClick={handleQuery}>
        查询
      </button>

      
      <button className='chart-btn' onClick={handleClick}>查看图表</button>
      {isChartVisible && <Chart  data={sumdata} /> }

    </div>
 
      
   


      {tableData.length > 0 && (
        <table>
          <thead>
            <tr>
            <th>Date</th>
            <th>Client Id</th>
            <th>Client Side</th>
            <th>Ticker</th>
            <th>RIC</th>
            <th>Size</th>
            <th>Price</th>
            <th>National USD</th>
            <th>Currency</th>
            <th>Issuer Sector</th>
            <th>Salesperson</th>
            <th>HT/PT</th>
            </tr>
          </thead>
          <tbody>
            {tableData.map((item) => (
              <tr key={item.id}>
                <td>{item.date}</td>
              <td>{item.clientId}</td>
              <td>{item.clientSide}</td>
              <td>{item.ticker}</td>
              <td>{item.ric}</td>
              <td>{item.size}</td>
              <td>{item.price}</td>
              <td>{item.notionalUsd}</td>
              <td>{item.currency}</td>
              <td>{item.issueSector}</td>
              <td>{item.salesperson}</td>
              <td>{item.htPt}</td>
              </tr>
            ))}
          </tbody>
        </table>
        
        //数据总和
        
      )}
               <span className='one'>Total Buy: {Totalbuy}</span>
               <span className='two'>Total Sell: {Totalsell}</span>
               <span className='one'>Net Quantity: {NetQuantity}</span>
               <span className='two'>Total Buy National: {NTotalbuy}</span>
               <span className='one'>Total Sell National: {NTotalsell}</span>
               <span className='two'> Net National: {NetNation}</span>
               <span className='one'> Total Records: {record}</span>
      
    </Fragment>
  );
};

export default Home;