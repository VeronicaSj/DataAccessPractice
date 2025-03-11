import { Typography, } from '@mui/material';
import React from 'react';

function Info({turno, winner, tie}) {
    if (winner==true) {
        let msg = 'Winner: O';
            if (turno=='O') msg = 'Winner: X'
        return (
            <Typography align='center' variant="h3" component="h2">{msg}</Typography> 
        );
    }

    if (tie==true){
        return (
            <Typography align='center' variant="h3" component="h2">Empate</Typography> 
        );
    }

  return (
    <Typography align='center' variant="h3" component="h2">Turno: {turno}</Typography>
  )
}

export default Info;