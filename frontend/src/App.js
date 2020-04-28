import React from 'react';
import './css/App.css';
import getUsers from './UsersProvider'
import Grid from '@material-ui/core/Grid';

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: true,
            data: getUsers()
        };
    }

    componentDidMount() {
        // fetch("http://localhost:8080/rest/board")
        //     .then(res => res.json())
        //     .then(res => this.setState({
        //         isLoaded: true,
        //         data: res
        //     }),
        //         (error) => {
        //             this.setState({
        //                 isLoaded: true,
        //                 error
        //             });
        //         }
        //     )
    }

    render() {
        const { error, isLoaded, data } = this.state;

        if (error) {
            return <div>{ error.message }</div>
        } else if (!isLoaded) {
            return <div>Loading</div>
        }

        return <Grid item xs={12}>
            <Grid container justify="center" spacing={6}>
                {data.map((value) => (
                    <Grid key={value} item>
                        <div class="list">
                            <h3 class="list-title">{value.label}</h3>

                            <ul class="list-items">
                                {value.students.map(s => (
                                    <li>
                                        Student: {s.login} <span />
                                    </li>
                                ))}
                            </ul>
                            <button class="add-card-btn btn">Add a card</button>
                        </div>
                    </Grid>
                ))}
            </Grid>
        </Grid>
    }
}

export default App;
