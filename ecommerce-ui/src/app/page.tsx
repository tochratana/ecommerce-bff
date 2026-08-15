export default async function Home() {
  const res = await fetch("http://localhost:9092/ecommerce-api/api/v1/categories");
  if (!res.ok) {
    return <div>Error {res.status}: API is not reachable. Is ecommerce-api running on :9090?</div>;
  }

  const data = await res.json();
  console.log(data);

  return (
    <div>
      <h1>Categories</h1>
      <pre>{JSON.stringify(data, null, 2)}</pre>
    </div>
  );
}
