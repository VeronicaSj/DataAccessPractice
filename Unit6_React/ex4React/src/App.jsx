import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Bar from "../components/myBar";
import Content from "../components/myContent";


function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <Bar></Bar>
      <Content></Content>
    </>
  )
}

export default App
