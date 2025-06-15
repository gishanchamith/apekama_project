import React, { useState, useEffect } from 'react';

function App() {
  const [communities, setCommunities] = useState([]);
  const [posts, setPosts] = useState([]);
  const [query, setQuery] = useState('');

  useEffect(() => {
    fetch('/api/communities')
      .then(res => res.json())
      .then(data => {
        setCommunities(data);
        if (data.length > 0) loadPosts(data[0].id);
      })
      .catch(() => setCommunities([]));
  }, []);

  const loadPosts = (cid) => {
    fetch(`/api/communities/${cid}/posts`)
      .then(res => res.json())
      .then(setPosts)
      .catch(() => setPosts([]));
  };

  const searchPosts = (e) => {
    e.preventDefault();
    if (!query) return;
    fetch(`/api/posts/search?q=${encodeURIComponent(query)}`)
      .then(res => res.json())
      .then(setPosts)
      .catch(() => setPosts([]));
  };

  return (
    <div>
      <h1>Apekama Communities</h1>
      <form onSubmit={searchPosts} style={{marginBottom:'1em'}}>
        <input
          type="text"
          value={query}
          onChange={e => setQuery(e.target.value)}
          placeholder="Search posts..."
        />
        <button type="submit">Search</button>
      </form>
      <ul>
        {communities.map(c => (
          <li key={c.id} onClick={() => loadPosts(c.id)} style={{cursor:'pointer'}}>
            {c.name}
          </li>
        ))}
      </ul>

      <h2>Posts</h2>
      {posts.map(p => (
        <div key={p.id} style={{border:'1px solid #ccc', margin:'10px', padding:'10px'}}>
          <h3>{p.title}</h3>
          {p.videoUrl ? (
            <iframe src={p.videoUrl} title={p.title} width="560" height="315" allowFullScreen></iframe>
          ) : (
            <p>{p.content}</p>
          )}
        </div>
      ))}
    </div>
  );
}

export default App;
