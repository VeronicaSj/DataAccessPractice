import { Grid2, Button, } from '@mui/material';
import React from 'react';

function BoardBtn({id, value, onClick, disabled }) {
  return (
        <Grid2 size={4} key={id} sx={{justifyContent: "center", alignItems: "center", }}>
            <Button variant="contained"  
                style={{maxWidth: '100px', maxHeight: '100px', minWidth: '100px', minHeight: '100px'}} 
                disabled={value || disabled } 
                onClick={onClick}>
                {value}
            </Button>
        </Grid2>
  )
}

export default BoardBtn;