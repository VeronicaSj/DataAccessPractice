import { Box, Container, Grid2, TextField, Typography } from '@mui/material';
import React, { useEffect } from 'react';
import { useState } from 'react'
import Button from '@mui/material/Button';
import Tablero from './Tablero';
import Info from './info';
import { RestartAlt } from '@mui/icons-material';


function MainView() {

  const [turno, setturno] = useState('X');
  const [winner, setwinner] = useState(false);
  const [tie, settie] = useState(false);
  
  function changeTurno(){
    console.log('changeTurno')
    console.log('Turno'+turno)
    if(turno=='X'){
      setturno('O');
      console.log('case x changed to '+turno)
    }else {
      setturno('X');
      console.log('case O changed to'+turno)
    }
    console.log('Turno'+turno)
  }

  function RestartGame(){
    setwinner(false)
    settie(false)

  }

  return (
    <Container>
      <Typography align='center' variant="h1" component="h2">TRES EN RAYA</Typography>
      <Info turno={turno} winner={winner} tie={tie}></Info>
      <Tablero 
        turno={turno} 
        changeTurno={changeTurno}
        winner={winner}
        setwinner={setwinner}
        tie={tie}
        settie={settie}
        />
      <Button variant="contained" onClick={()=>RestartGame()} disabled={winner==false && tie==false}>Restart game</Button>
    </Container>
  )
}

export default MainView;
