# djd

djd

## Setup
```bash
python -m venv venv
source venv/bin/activate  # On Windows: venv\Scripts\activate
pip install -r requirements.txt


# Run migrations
python manage.py makemigrations
python manage.py migrate

# Create superuser
python manage.py createsuperuser

# Run server
python manage.py runserver
```

## API Endpoints

- `GET /api/hotel` - List all Hotel
- `GET /api/hotel/{id}` - Get Hotel by ID
- `POST /api/hotel` - Create Hotel
- `PUT /api/hotel/{id}` - Update Hotel
- `DELETE /api/hotel/{id}` - Delete Hotel

- `GET /api/room` - List all Room
- `GET /api/room/{id}` - Get Room by ID
- `POST /api/room` - Create Room
- `PUT /api/room/{id}` - Update Room
- `DELETE /api/room/{id}` - Delete Room

- `GET /api/customer` - List all Customer
- `GET /api/customer/{id}` - Get Customer by ID
- `POST /api/customer` - Create Customer
- `PUT /api/customer/{id}` - Update Customer
- `DELETE /api/customer/{id}` - Delete Customer

- `GET /api/booking` - List all Booking
- `GET /api/booking/{id}` - Get Booking by ID
- `POST /api/booking` - Create Booking
- `PUT /api/booking/{id}` - Update Booking
- `DELETE /api/booking/{id}` - Delete Booking


