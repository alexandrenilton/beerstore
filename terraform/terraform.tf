terraform {
  backend "s3" {
    bucket = "terraform-alexandre"
    key = "beerstore-alexandre"
    region = "us-east-1"
    profile = "terraform"
  }
}