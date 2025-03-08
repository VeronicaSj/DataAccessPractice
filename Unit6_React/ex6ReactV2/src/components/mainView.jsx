import { Box, Container, TextField, Typography } from '@mui/material';
import React, { useEffect } from 'react';
import { useState } from 'react'
import Button from '@mui/material/Button';
import ApiRes from './apiRes';

function MainView() {

  const [dogbreedinput, setdogbreedinput] = useState("");
  const [loading, setLoading] = useState(false);
  
  const [dogbreed, setDogBreed] = useState(null);
  
  function onSubmit() {
    setDogBreed(dogbreedinput);
  }

  return (
    <>
        <Container sx={{ mt: 2 }} >
            <Typography variant="h3" component="h1" align="center" gutterBottom> </Typography>
            <div sx={{ display: "grid" , gap: 2 }} component="form">

                <TextField 
                    id='dogBreed' label="Dog Breed" variant="outlined" 
                    required fullWidth size="small" 
                    value={dogbreedinput} onChange={(e) => setdogbreedinput(e.target.value)}>
                </TextField>

                <Button 
                    variant="contained" 
                    onClick = {onSubmit}>
                  Buscar 
                </Button>
            </div>
            <ApiRes dogbreed={dogbreed}/>
        </Container>
    </>
  )
}

export default MainView;
