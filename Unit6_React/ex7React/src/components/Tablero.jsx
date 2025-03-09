import { Box, Container, Grid2, TextField, Typography, Button, } from '@mui/material';
import React, { useEffect } from 'react';
import { useState } from 'react'

function Tablero({turno, changeTurno, winner, setwinner, tie, settie}) {
    const defaultState = {value:'', disbled:false};

    const [clickedButons, setclickedButons] = useState([null, null, null, 
                    null, null, null, 
                    null, null, null]);

    function onClick(id){
        let newArray = clickedButons;
        newArray[id] = turno;
        setclickedButons(newArray);
        changeTurno()
        checkwinner()
        console.log(clickedButons)
    }

    function checkwinner(){
        const lines = [
            [0, 1, 2],
            [3, 4, 5],
            [6, 7, 8],
            [0, 3, 6],
            [1, 4, 7],
            [2, 5, 8],
            [0, 4, 8],
            [2, 4, 6],
          ];
          for (let i = 0; i < lines.length; i++) {
            const [a, b, c] = lines[i];
            if (clickedButons[a] && 
                clickedButons[a] === clickedButons[b] && 
                clickedButons[a] === clickedButons[c]) {
              setwinner(true)
              setclickedButons([null, null, null, 
                null, null, null, 
                null, null, null])
            }
          }
    }

  return (
    <Grid2 container  sx={{justifyContent: "center", alignItems: "center", }} spacing={2}>
        <Grid2 size={4} >
            <Button variant="contained"  
                style={{maxWidth: '100px', maxHeight: '100px', minWidth: '100px', minHeight: '100px'}} 
                disabled={clickedButons[0] || winner || tie} 
                onClick={()=>onClick(0)}>
                {clickedButons[0]}
            </Button>
        </Grid2>
        <Grid2 size={4}>
            <Button variant="contained" 
                style={{maxWidth: '100px', maxHeight: '100px', minWidth: '100px', minHeight: '100px'}} 
                disabled={clickedButons[1] || winner || tie} 
                    onClick={()=>onClick(1)}>
                {clickedButons[1]}
            </Button>
        </Grid2>
        <Grid2 size={4}>
            <Button variant="contained" 
                style={{maxWidth: '100px', maxHeight: '100px', minWidth: '100px', minHeight: '100px'}} 
                disabled={clickedButons[2] || winner || tie} 
                    onClick={()=>onClick(2)}>
                {clickedButons[2]}
            </Button>
        </Grid2>
        <Grid2 size={4}>
            <Button variant="contained" 
                style={{maxWidth: '100px', maxHeight: '100px', minWidth: '100px', minHeight: '100px'}} 
                disabled={clickedButons[3] || winner || tie} 
                    onClick={()=>onClick(3)}>
                {clickedButons[3]}
            </Button>
        </Grid2>

        <Grid2 size={4}>
            <Button variant="contained" 
                style={{maxWidth: '100px', maxHeight: '100px', minWidth: '100px', minHeight: '100px'}} 
                disabled={clickedButons[4] || winner || tie} 
                    onClick={()=>onClick(4)}>
                {clickedButons[4]}
            </Button>
        </Grid2>
        <Grid2 size={4}>
            <Button variant="contained" 
                style={{maxWidth: '100px', maxHeight: '100px', minWidth: '100px', minHeight: '100px'}} 
                disabled={clickedButons[5] || winner || tie} 
                    onClick={()=>onClick(5)}>
                {clickedButons[5]}
            </Button>
        </Grid2>
        <Grid2 size={4}>
            <Button variant="contained" 
                style={{maxWidth: '100px', maxHeight: '100px', minWidth: '100px', minHeight: '100px'}} 
                disabled={clickedButons[6] || winner || tie} 
                    onClick={()=>onClick(6)}>
                {clickedButons[6]}
            </Button>
        </Grid2>

        <Grid2 size={4}>
            <Button variant="contained" 
                style={{maxWidth: '100px', maxHeight: '100px', minWidth: '100px', minHeight: '100px'}} 
                disabled={clickedButons[7] || winner || tie} 
                    onClick={()=>onClick(7)}>
                {clickedButons[7]}
            </Button>
        </Grid2>
        <Grid2 size={4}>
            <Button variant="contained" 
                style={{maxWidth: '100px', maxHeight: '100px', minWidth: '100px', minHeight: '100px'}} 
                disabled={clickedButons[8] || winner || tie} 
                    onClick={()=>onClick(8)}>
                {clickedButons[8]}
            </Button>
        </Grid2>
    </Grid2>
  )
}

export default Tablero;