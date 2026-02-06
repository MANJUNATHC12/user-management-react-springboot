import { useEffect, useState } from "react";
import axios from "axios";



function Form(){
    const[user, setuser] = useState([]);
    const[form, setform] = useState({name:"", email:"", age:""})
    const[editid, seteditid] = useState("")
    // const[error,seterror] = useState({});
    const[age, setage] = useState("");  

    const[searchTerm, setsearchTerm] = useState("");

    const loaduser = async() => {
        const res = await axios.get("/user");
        setuser(res.data)
    };

    useEffect(() => {
        loaduser();
    },[]);

    const edituser = (u) => {
        setform(u);
        seteditid(u.id);
    }

    const deleteuser = async (id) => {
        await axios.delete(`/user/${id}`);
        loaduser();
    }

    const submit = async () => {
        if(editid){
            await axios.put(`/user/${editid}`, form);
            seteditid(null);
        }
        else {
            await axios.post("/user", form);
        }
        setform({name:"", email:"", age:""})
        loaduser();
    };


    const handlesearch = async () => {
        
        if(searchTerm && !age){
            await axios.get("/user/search",{
                params:{keyword: searchTerm}
            })
            .then(res => setuser(res.data));
            return;
        }
        
        if(!searchTerm && age){
            await axios.get("/user/byage",{
                params: {age}
            })
            .then(res => setuser(res.data));
            return;
        }

      
    };



    return(
        <>
        <form>

        
            <label>NAME:</label>
            <input type="text" placeholder="enter your name" value={form.name} onChange={(e) => setform({...form, name: e.target.value})}/>
                <label>EMAIL:</label>
            <input type="email" placeholder="enter your name" value={form.email} onChange={(e) => setform({...form, email: e.target.value})}  />
                <label>AGE:</label>
            <input type="number" placeholder="enter your name" value={form.age} onChange={(e) => setform({...form, age: e.target.value})}/>

            <button onClick={submit}>
                {editid ? "Update" : "Add"}
            </button>
        </form>



    <input type="text" placeholder="search by name or email" value={searchTerm} onChange={(e) => setsearchTerm(e.target.value)}/>
          
                <br/>
            <input type="number" placeholder="search by age" value={age} onChange={(e) => setage(e.target.value)}/>


            <button onClick={handlesearch}>search</button>
            

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Age</th>
                    <th>Actions</th>
                </tr>
            </thead>

            <tbody>
                {user.map(u => (
                     <tr key={u.id}>
                    <td>{u.id}</td>
                    <td>{u.name}</td>
                    <td>{u.email}</td>
                    <td>{u.age}</td>
                    
                    <td>
                        <button onClick={() => edituser(u)}>Edit</button>
                    <button onClick={() => deleteuser(u.id)}>Delete</button>
                    </td>
                </tr>
                ))}
            </tbody>
        </table>
        
        </>
    )
}

export default Form;
