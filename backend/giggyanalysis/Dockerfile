FROM python:3.7

# set work directory
WORKDIR /code

# install dependencies
RUN pip install --upgrade pip
COPY requirements.txt /code/
RUN pip install -r requirements.txt

# copy project
COPY . /code/

# Install Java for PyKomoran
# ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
# RUN apt update -y && apt-get install -y software-properties-common && \
#     apt-add-repository 'deb http://security.debian.org/debian-security stretch/updates main' && apt update -y && \
#     apt-get install -y openjdk-8-jdk-headless
#     # pip install --no-cache-dir -r requirements.txt && \
#     # export JAVA_HOME && \
#     # apt-get clean

CMD ["gunicorn", "--bind", "0.0.0.0:8000", "stock.wsgi:application"]