import React, { useState } from 'react';
import { Bar, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ComposedChart } from 'recharts';
import './chart.styl'
const Chart = ({ data }) => {
  const [activePoint, setActivePoint] = useState(null);

  const handlePointHover = (dataPoint) => {
    setActivePoint(dataPoint);
  };

  //对传入数据处理
  const processedData = data.map(item => {
    let buy = item.totalBuyNotional;
    let sell = -item.totalSellNotional;
    let Cumulative_Net =item. netNotional;
    let date = item.date;
    
    return {
        date,
        buy,
        sell,
        Cumulative_Net,
      };
    });

  return (
    <div>
      <ComposedChart width={800} height={400} data={processedData}>
      <XAxis dataKey="date" />
      <YAxis yAxisId="left"  orientation="left" />
      <YAxis yAxisId="right"  orientation="right" />
      <CartesianGrid strokeDasharray="3 3" />
      <Tooltip />
      <Legend />
      <Bar yAxisId="right" dataKey="buy" fill="#8884d8" />
      <Bar yAxisId="left" dataKey="sell" fill="#82ca9d" />
      <Line yAxisId="right" type="monotone" dataKey="Cumulative_Net" stroke="#ff7300" activeDot={{ r: 8 }} />
    </ComposedChart>

  
      {activePoint && (
        <div>
          <p>{activePoint.date}</p>
          <p>buy: {activePoint.buy}</p>
          <p>sell: {activePoint.sell}</p>
          <p>Cumulative Net: {activePoint.Cumulative_Net}</p>
        </div>
      )}
    </div>
  );
};

export default Chart;