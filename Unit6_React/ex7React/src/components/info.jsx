import { Box, Container, Grid2, TextField, Typography, Button, } from '@mui/material';
import React, { useEffect } from 'react';
import { useState } from 'react'

function Info({turno, winner, tie}) {
    if (tie==true){
        return (
            <Typography align='center' variant="h3" component="h2">Empate</Typography> 
        );
    }
    if (winner==true) {
        let msg = 'Winner: O';
            if (turno=='O') msg = 'Winner: X'
        return (
            <Typography align='center' variant="h3" component="h2">{msg}</Typography> 
        );
    }

  return (
    <Typography align='center' variant="h3" component="h2">Turno: {turno}</Typography>
  )
}

export default Info;