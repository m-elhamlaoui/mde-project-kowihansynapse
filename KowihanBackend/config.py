import os
from pathlib import Path

class Config:
    SECRET_KEY = os.getenv('SECRET_KEY', 'default-secret-key')
    BASE_DIR = Path(__file__).parent
    
    @classmethod
    def init_app(cls):
        for folder in ['uploads', 'generated']:
            (cls.BASE_DIR / folder).mkdir(exist_ok=True)
