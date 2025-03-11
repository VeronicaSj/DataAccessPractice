import { Container, Divider, Grid2, Typography } from '@mui/material';
import { useState } from 'react'
import Button from '@mui/material/Button';
import Tablero from './Tablero';
import Info from './info';

function MainView() {

  const [turno, setturno] = useState('X');
  const [winner, setwinner] = useState(false);
  const [tie, settie] = useState(false);
  
  function changeTurno(){
    if(turno=='X'){
      setturno('O');
    }else {
      setturno('X');
    }
  }

  function RestartGame(){
    setwinner(false)
    settie(false)

  }

  return (
    <Grid2 container spacing={2} columns={1} alignItems={'center'} justifyContent={'center'}>
      <Grid2 size={1}>
        <Typography align='center' variant="h1" component="h2">TRES EN RAYA</Typography>
      </Grid2>
      <Grid2 size={1}>
        <Info turno={turno} winner={winner} tie={tie}></Info>
      </Grid2>
      <Grid2 size={1}>
        <Tablero 
          turno={turno} 
          changeTurno={changeTurno}
          winner={winner}
          setwinner={setwinner}
          tie={tie}
          settie={settie}
          />
      </Grid2>
      <Grid2 size={1} alignContent={'center'} alignItems={'center'} justifyContent={'center'}>
        <Button variant="contained" align='center'
          onClick={()=>RestartGame()} 
          disabled={winner==false && tie==false}>
            Restart game
        </Button>
      </Grid2>
    </Grid2>
  )
}

export default MainView;
