import React, {useState} from 'react';
import Select from 'react-select'
import axios from 'axios';

const InviteMember = (props) => {
    const [username, setUsername] = useState('');
    const roleOptions = [
        {value: '', label: 'Choose role'},
        {value: 'editor', label: 'Editor'},
        {value: 'advertiser', label: 'Advertiser'},
        {value: 'designer', label: 'Designer'}
    ];
    const [role, setRole] = useState(roleOptions[0].value);

    const handleSubmit = async (e) => {
        e.preventDefault();

        await axios.post('http://localhost:8080/permissions', {}, {
            params: {
                user_email: username,
                publication_id: props.id_publication,
                role: role
            },
            headers: {
                Authorization: localStorage.getItem("auth")
            }
        }).then(response => {
            // console.log(response)
        });
    }

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <div className={'username-input'}>
                    <p>Invite member</p>
                    <label htmlFor={'username'}>
                        <input type={'text'} placeholder={'Username'} value={username}
                               onChange={(e) => setUsername(e.target.value)}
                               required={true}/>
                    </label>
                </div>

                <div className={'role-options'}>
                    <p>Choose role</p>
                    <label htmlFor={'role'}>
                        <select value={role}
                                onChange={(e) => setRole(e.target.value)}
                                required={true}>
                            {roleOptions.map(option => (
                                <option key={option.value} value={option.label}>
                                    {option.label}
                                </option>
                            ))}
                        </select>
                        {/*<Select options={roleOptions}*/}
                        {/*        onChange={(e) => setRole(e.target.value)}/>*/}
                    </label>
                </div>

                <div className={'invite-button'}>
                    <button className={'button-button'}>Invite</button>
                </div>
            </form>
        </div>
    );
};

export default InviteMember;