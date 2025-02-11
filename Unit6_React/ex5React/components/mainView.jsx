import { LoadingButton } from '@mui/lab';
import { Box, Container, TextField, Typography } from '@mui/material';
import React from 'react';
import { useState } from 'react'
import ConsultWeather from './weatherConsultant';
import Button from '@mui/material/Button';
import WeatherCard from './weatherCard';


function MainView() {

  const [city, setCity] = useState("");
  const [loading, setLoading] = useState(false);
  const [weatherCard, setWeatherCard] = useState(null);

  function onSubmit() {
    setLoading(true)
    console.log('setLoading(true)')
    setWeatherCard(<ConsultWeather 
                        city={city} 
                        renderData={(weather) => <WeatherCard weather={weather}/>}
                        ></ConsultWeather>
        );
    
  }

  if(weatherCard != null && loading){
    setLoading(false)
    console.log('setLoading(false)')
  }

  return (
    <>
      <Container sx={{ mt: 2 }} >

            <Typography variant="h3" component="h1" align="center" gutterBottom> </Typography>
            <Box sx={{ display: "grid" , gap: 2 }} component="form">

                <TextField 
                    id='city' label="Ciudad" variant="outlined" 
                    required fullWidth size="small" value={city} 
                    onChange={(e) => setCity(e.target.value)}></TextField>

                <Button 
                    variant="contained" 
                    loading={loading}
                    loadingIndicator="Cargando..."
                    onClick={onSubmit}> 
                  Buscar 
                </Button>
            </Box>
            {weatherCard}
        </Container>
    </>
  )
}

export default MainView;
